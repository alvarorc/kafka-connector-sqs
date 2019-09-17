package com.tarmac.kafka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jcustenborder.kafka.connect.utils.VersionUtil;
import com.github.jcustenborder.kafka.connect.utils.config.Description;
import com.github.jcustenborder.kafka.connect.utils.config.DocumentationImportant;
import com.github.jcustenborder.kafka.connect.utils.config.DocumentationNote;
import com.github.jcustenborder.kafka.connect.utils.config.DocumentationTip;
import com.github.jcustenborder.kafka.connect.utils.config.Title;

/**
 *
 */

@Description("This is a description of this connector and will show up in the documentation")
@DocumentationImportant("This is a important information that will show up in the documentation.")
@DocumentationTip("This is a tip that will show up in the documentation.")
@Title("Super Sink Connector") //This is the display name that will show up in the documentation.
@DocumentationNote("This is a note that will show up in the documentation")
public class SQSSinkConnector extends SinkConnector {
    /*
    Your connector should never use System.out for logging. All of your classes should use slf4j
    for logging
     */
    private static Logger log = LoggerFactory.getLogger(SQSSinkConnector.class);
    private SQSSinkConnectorConfig config;

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        List<Map<String, String>> taskConfigs = new ArrayList<>();
        Map<String, String> taskProps = new HashMap<>(config.originalsStrings());

        for (int i = 0; i < maxTasks; i++) {
            taskConfigs.add(taskProps);
        }

        return taskConfigs;
    }

    @Override
    public void start(Map<String, String> settings) {
        config = new SQSSinkConnectorConfig(settings);

        //TODO: Add things you need to do to setup your connector.

        /**
         * This will be executed once per connector. This can be used to handle connector level setup. For
         * example if you are persisting state, you can use this to method to create your state table. You
         * could also use this to verify permissions
         */
        log.info("Staring SQS-sink connector {}", config.getName());

    }

    @Override
    public void stop() {
        //TODO: Do things that are necessary to stop your connector.
        log.info("Shutting down SQS-sink connector {}", config.getName());
    }

    @Override
    public ConfigDef config() {
        return SQSSinkConnectorConfig.config();
    }

    @Override
    public Class<? extends Task> taskClass() {
        //TODO: Return your task implementation.
        return SQSSinkTask.class;
    }

    @Override
    public String version() {
        return VersionUtil.version(this.getClass());
    }
}
