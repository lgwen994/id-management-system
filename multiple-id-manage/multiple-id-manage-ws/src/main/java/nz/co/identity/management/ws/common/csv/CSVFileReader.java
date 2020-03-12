/**
 * @(#)CSVFileReader.java
 * (C)Copyright 2017 Toshiba Digital Solutions Corporation
 *
 * 機    能：CSVFileReader is used for parse csv file.
 * クラス名：CSVFileReader
 *
 *   ver     変更日         所属       担当者        変更内容
 * ──────────────────────────────────
 *  V1.0   2017/10/08      Neusoft    初版
 *
 *┌─────────────────────────────────┐
 *│  本技術情報には当社の機密情報が含まれておりますので、当社の      │
 *│  書面による承諾がなく第三者に開示することはできません。          │
 *│  また、当社の承諾を得た場合であっても、本技術情報は外国為替      │
 *│  及び外国貿易管理法に定める特定技術に該当するため、非居住者に    │
 *│  提供する場合には、同法に基づく許可を要することがあります。      │
 *│                      東芝デジタルソリューションズ  株式会社      │
 *└─────────────────────────────────┘
 */
package nz.co.identity.management.ws.common.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.StringUtils;

import nz.co.identity.management.api.common.exception.ImRuntimeException;

/**
 * 
 * CSVFileReader is used for parse csv file.
 * 
 * 
 * @since Staveware Core Ver.5.3
 */
public class CSVFileReader {
    /**
     * constructor
     * 
     * @since Staveware Core Ver.5.3
     */
    private CSVFileReader() {
    }

    /**
     * INDEX
     * 
     * @since Staveware Core Ver.5.3
     */
    public static final int INDEX = 11;

    /**
     * convert the bean from csv file.
     * 
     * @param inputStream
     *        inputStream
     * @param clazz
     *        the class of bean
     * @param <T>
     *        the type.
     * @return bean list
     * @since Staveware Core Ver.5.3
     */
    public static <T> List<T> getBeansFromCSVFile(InputStream inputStream,
            Class<T> clazz) {

        List<T> retList = new ArrayList<T>();
        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();
        Reader in = new InputStreamReader(inputStream);
        Iterable<CSVRecord> records = null;
        try {
            records = format.parse(in);
        } catch (IOException e) {
            throw new ImRuntimeException(e);
        }

        T instance = null;
        Map<String, String> map = null;
        Iterator<Entry<String, String>> iterator = null;
        Entry<String, String> entry = null;
        Type type = null;
        String key = null;
        String value = null;
        for (CSVRecord record : records) {
            try {
                instance = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new ImRuntimeException(e);
            }
            map = record.toMap();
            iterator = map.entrySet().iterator();
            Map<String, Object> extendsMap = new HashMap<String, Object>();
            while (iterator.hasNext()) {
                entry = iterator.next();
                key = entry.getKey();
                value = entry.getValue();
                if (key.startsWith("extendsMap.")) {
                    extendsMap.put(key.substring(INDEX), value);
                } else {
                    try {
                        type = instance.getClass().getDeclaredField(key)
                                .getGenericType();
                    } catch (NoSuchFieldException | SecurityException e) {
                        throw new ImRuntimeException(e);
                    }
                    if (type.getTypeName().equals("java.util.Date")) {
                        SimpleDateFormat sdf = new SimpleDateFormat(
                                "yyyyMMdd HH:mm:ss");
                        if (!StringUtils.isEmpty(value)) {
                            Date dateValue = null;
                            try {
                                dateValue = sdf.parse(value);
                            } catch (ParseException e) {
                                throw new ImRuntimeException(e);
                            }
                            try {
                                BeanUtils.copyProperty(instance, key,
                                        dateValue);
                            } catch (IllegalAccessException
                                    | InvocationTargetException e) {
                                throw new ImRuntimeException(e);
                            }
                        }
                    } else {
                        try {
                            BeanUtils.copyProperty(instance, key, value);
                        } catch (IllegalAccessException
                                | InvocationTargetException e) {
                            throw new ImRuntimeException(e);
                        }
                    }
                }

            }
            try {
                BeanUtils.copyProperty(instance, "extendsMap", extendsMap);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new ImRuntimeException(e);
            }
            retList.add(instance);
        }

        return retList;
    }
//
}
