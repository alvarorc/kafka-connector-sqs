package com.tarmac.kafka;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.config.ConfigDef.Importance;
import com.github.jcustenborder.kafka.connect.utils.config.ConfigKeyBuilder;

import java.util.Map;


public class SQSSinkConnectorConfig extends AbstractConfig {

    public static final String SQS_URL = "sqs.url";
    private static final String SQS_URL_DOC = "The SQS queue url to sink from kafka topic";
    public static final String AWS_ACCESS_KEY_ID = "aws.access.key.id";
    private static final String AWS_ACCESS_KEY_ID_DOC = "AWS access key id to connect to the SQS queue";
    public static final String AWS_ACCESS_KEY_TOKEN = "aws.access.key.token";
    private static final String AWS_ACCESS_KEY_TOKEN_DOC = "AWS access key token to connect to the SQS queue";

    private final String name;

    public SQSSinkConnectorConfig(Map<?, ?> originals) {
        super(config(), originals);
        this.name = parseName(originalsStrings());
    }

    public static ConfigDef config() {
        return new ConfigDef()
                .define(
                        ConfigKeyBuilder.of(SQS_URL, Type.STRING)
                                .documentation(SQS_URL_DOC)
                                .importance(Importance.HIGH)
                                .build()
                )
                .define(
                        ConfigKeyBuilder.of(AWS_ACCESS_KEY_ID, Type.STRING)
                                .documentation(AWS_ACCESS_KEY_ID_DOC)
                                .importance(Importance.HIGH)
                                .defaultValue("")
                                .build()
                )
                .define(
                        ConfigKeyBuilder.of(AWS_ACCESS_KEY_TOKEN, Type.STRING)
                                .documentation(AWS_ACCESS_KEY_TOKEN_DOC)
                                .importance(Importance.HIGH)
                                .defaultValue("")
                                .build()
                );

    }

    private static String parseName(Map<String, String> props) {
        String nameProp = props.get("name");
        return nameProp != null ? nameProp : "SQS-sink";
    }

    public String getSqsUrl() {
        return this.getString(SQS_URL);
    }

    public String getAwsAccessKeyId() {
        return this.getString(AWS_ACCESS_KEY_ID);
    }

    public String getAwsAccessKeyToken() {
        return this.getString(AWS_ACCESS_KEY_TOKEN);
    }

    public String getName() {
        return name;
    }
}
