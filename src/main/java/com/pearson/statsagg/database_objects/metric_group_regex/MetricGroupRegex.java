package com.pearson.statsagg.database_objects.metric_group_regex;

import com.google.gson.annotations.SerializedName;
import com.pearson.statsagg.database_engine.DatabaseObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jeffrey Schmidt
 */
public class MetricGroupRegex extends DatabaseObject<MetricGroupRegex> {
    
    private static final Logger logger = LoggerFactory.getLogger(MetricGroupRegex.class.getName());
    
    @SerializedName("id") private transient Integer id_;
    @SerializedName("metric_group_id") private transient Integer metricGroupId_;
    @SerializedName("is_blacklist_regex") private Boolean isBlacklistRegex_;
    @SerializedName("pattern") private String pattern_;
    
    public MetricGroupRegex() {
        this.id_ = -1;
    }
    
    public MetricGroupRegex(Integer id, Integer metricGroupId, Boolean isBlacklistRegex, String pattern) {
        this.id_ = id;
        this.metricGroupId_ = metricGroupId;
        this.isBlacklistRegex_ = isBlacklistRegex;
        this.pattern_ = pattern;
    } 
    
    @Override
    public boolean isEqual(MetricGroupRegex metricGroupRegex) {
        
        if (metricGroupRegex == null) return false;
        if (metricGroupRegex == this) return true;
        if (metricGroupRegex.getClass() != getClass()) return false;
        
        return new EqualsBuilder()
                .append(id_, metricGroupRegex.getId())
                .append(metricGroupId_, metricGroupRegex.getMetricGroupId())
                .append(isBlacklistRegex_, metricGroupRegex.isBlacklistRegex())
                .append(pattern_, metricGroupRegex.getPattern())
                .isEquals();
    }
    
    public static MetricGroupRegex copy(MetricGroupRegex metricGroupRegex) {
        
        if (metricGroupRegex == null) {
            return null;
        }
        
        MetricGroupRegex metricGroupRegexCopy = new MetricGroupRegex();
        
        metricGroupRegexCopy.setId(metricGroupRegex.getId());
        metricGroupRegexCopy.setMetricGroupId(metricGroupRegex.getMetricGroupId());
        metricGroupRegexCopy.setIsBlacklistRegex(metricGroupRegex.isBlacklistRegex());
        metricGroupRegexCopy.setPattern(metricGroupRegex.getPattern());
        
        return metricGroupRegexCopy;
    }

    public Integer getId() {
        return id_;
    }

    public void setId(Integer id) {
        this.id_ = id;
    }

    public Integer getMetricGroupId() {
        return metricGroupId_;
    }

    public void setMetricGroupId(Integer mgId) {
        this.metricGroupId_ = mgId;
    }

    public Boolean isBlacklistRegex() {
        return isBlacklistRegex_;
    }

    public void setIsBlacklistRegex(Boolean isBlacklistRegex) {
        this.isBlacklistRegex_ = isBlacklistRegex;
    }

    public String getPattern() {
        return pattern_;
    }

    public void setPattern(String pattern) {
        this.pattern_ = pattern;
    }

}
