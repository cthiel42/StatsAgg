function confirmAction(formName, confirmString) {
    var confirmed = confirm(confirmString);

    if (confirmed) {
        document.forms[formName].submit();
    }

    return confirmed;
}

function generateAlertPreviewLink(warningLevel) {

    var warningLevelParameter = "WarningLevel=" + encodeURIComponent(warningLevel);

    var nameParameter = "Name=" + encodeURIComponent(document.getElementById("Name").value.substring(0, 500));
    var descriptionParameter = "Description=" + encodeURIComponent(document.getElementById("Description").value.substring(0, 500));
    
    var cautionEnabledParameter = "CautionEnabled=" + encodeURIComponent(document.getElementById("CautionEnabled").value);
    var dangerEnabledParameter = "DangerEnabled=" + encodeURIComponent(document.getElementById("DangerEnabled").value);
    
    var alertTypeParameter;
    if (document.getElementById("CreateAlert_Type_Availability").checked === true) alertTypeParameter = "CreateAlert_Type=" + encodeURIComponent("Availability");
    else if (document.getElementById("CreateAlert_Type_Threshold").checked === true) alertTypeParameter = "CreateAlert_Type=" + encodeURIComponent("Threshold");
    else alertTypeParameter = "CreateAlert_Type=" + encodeURIComponent("undefined");
    
    var metricGroupNameParameter = "MetricGroupName=" + encodeURIComponent(document.getElementById("MetricGroupName").value.substring(0, 500));
    
    var cautionWindowDurationParameter = "CautionWindowDuration=" + encodeURIComponent(document.getElementById("CautionWindowDuration").value);
    var cautionStopTrackingAfterParameter = "CautionStopTrackingAfter=" + encodeURIComponent(document.getElementById("CautionStopTrackingAfter").value);
    var cautionMinimumSampleCountParameter = "CautionMinimumSampleCount=" + encodeURIComponent(document.getElementById("CautionMinimumSampleCount").value);
    var cautionOperatorParameter = "CautionOperator=" + encodeURIComponent(document.getElementById("CautionOperator").value);
    var cautionCombinationParameter = "CautionCombination=" + encodeURIComponent(document.getElementById("CautionCombination").value);
    var cautionCombinationCountParameter = "CautionCombinationCount=" + encodeURIComponent(document.getElementById("CautionCombinationCount").value);
    var cautionThresholdParameter = "CautionThreshold=" + encodeURIComponent(document.getElementById("CautionThreshold").value);

    var dangerWindowDurationParameter = "DangerWindowDuration=" + encodeURIComponent(document.getElementById("DangerWindowDuration").value);
    var dangerStopTrackingAfterParameter = "DangerStopTrackingAfter=" + encodeURIComponent(document.getElementById("DangerStopTrackingAfter").value);
    var dangerMinimumSampleCountParameter = "DangerMinimumSampleCount=" + encodeURIComponent(document.getElementById("DangerMinimumSampleCount").value);
    var dangerOperatorParameter = "DangerOperator=" + encodeURIComponent(document.getElementById("DangerOperator").value);
    var dangerCombinationParameter = "DangerCombination=" + encodeURIComponent(document.getElementById("DangerCombination").value);
    var dangerCombinationCountParameter = "DangerCombinationCount=" + encodeURIComponent(document.getElementById("DangerCombinationCount").value);
    var dangerThresholdParameter = "DangerThreshold=" + encodeURIComponent(document.getElementById("DangerThreshold").value);

    var uriEncodedLink = "AlertPreview?" + warningLevelParameter + "&" +
            nameParameter + "&" + descriptionParameter + "&" + cautionEnabledParameter + "&" + dangerEnabledParameter + "&" + alertTypeParameter + "&" + metricGroupNameParameter + "&" +
            cautionWindowDurationParameter + "&" +  cautionStopTrackingAfterParameter + "&" + cautionMinimumSampleCountParameter + "&" + 
            cautionOperatorParameter + "&" + cautionCombinationParameter + "&" + cautionCombinationCountParameter + "&" + cautionThresholdParameter + "&" +
            dangerWindowDurationParameter + "&" + dangerStopTrackingAfterParameter + "&" + dangerMinimumSampleCountParameter + "&" + 
            dangerOperatorParameter + "&" + dangerCombinationParameter + "&" + dangerCombinationCountParameter + "&" + dangerThresholdParameter;

    if (warningLevel === "Caution") {
        document.getElementById("CautionPreview").setAttribute("href", uriEncodedLink);
    }

    if (warningLevel === "Danger") {
        document.getElementById("DangerPreview").setAttribute("href", uriEncodedLink);
    }
}

function generateAlertSuspensionAssociationsPreviewLink() {

    var SuspendByParameter;
    if (document.getElementById("CreateAlertSuspension_SuspendBy_AlertName_Radio").checked === true) SuspendByParameter = "CreateAlertSuspension_SuspendBy=" + encodeURIComponent("AlertName");
    else if (document.getElementById("CreateAlertSuspension_SuspendBy_Tags_Radio").checked === true) SuspendByParameter = "CreateAlertSuspension_SuspendBy=" + encodeURIComponent("Tags");
    else if (document.getElementById("CreateAlertSuspension_SuspendBy_Everything_Radio").checked === true) SuspendByParameter = "CreateAlertSuspension_SuspendBy=" + encodeURIComponent("Everything");
    else SuspendByParameter = "CreateAlertSuspension_SuspendBy=" + encodeURIComponent("undefined");

    var AlertNameParameter = "AlertName=" + encodeURIComponent(document.getElementById("AlertName").value);
    var MetricGroupTagsInclusiveParameter = "MetricGroupTagsInclusive=" + encodeURIComponent(document.getElementById("MetricGroupTagsInclusive").value);
    var MetricGroupTagsExclusiveParameter = "MetricGroupTagsExclusive=" + encodeURIComponent(document.getElementById("MetricGroupTagsExclusive").value);

    var uriEncodedLink = "AlertSuspensionAlertAssociationsPreview?" + SuspendByParameter + "&" +
            AlertNameParameter + "&" + MetricGroupTagsInclusiveParameter + "&" + MetricGroupTagsExclusiveParameter;

    document.getElementById("AlertSuspensionAlertAssociationsPreview").setAttribute("href", uriEncodedLink);
}

function generateForgetMetricsPreviewLink() {
    var ForgetMetricRegexParameter = "Regex=" + encodeURIComponent(document.getElementById("ForgetMetricRegex").value);
    var uriEncodedLink = "ForgetMetricsPreview?" + ForgetMetricRegexParameter;
    document.getElementById("ForgetMetricsPreview").setAttribute("href", uriEncodedLink);    
}

function generateMergedRegexMetricsPreview() {
    var RegexParameter = "Regexs=" + encodeURIComponent(document.getElementById("Regexs").value);
    var uriEncodedLink = "MergedRegexMetricsPreview?" + RegexParameter;
    document.getElementById("MergedRegexMetricsPreview").setAttribute("href", uriEncodedLink);    
}

$(document).ready(function() {
    $(".iframe").colorbox({iframe: true, width: "80%", height: "80%"});
});

// On page load for 'CreateAlert', show and hide certain UI elements for 'type'
$(document).ready(function() {
    CreateAlert_Type_ShowAndHide();
    CreateAlert_CautionCriteria_ShowAndHide();
    CreateAlert_DangerCriteria_ShowAndHide();
});

// On changing the 'caution alerting enabled' checkbox, show or hide the 'caution criteria' panel
$('#CautionEnabled').change(function () {
    CreateAlert_CautionCriteria_ShowAndHide();
});

function CreateAlert_CautionCriteria_ShowAndHide() {
    if ($("#CautionEnabled").prop('checked') === true) {
        $("#CautionCriteria").show();
    }
    else {
        $("#CautionCriteria").hide();
    }
}

// On changing the 'danger alerting enabled' checkbox, show or hide the 'danger criteria' panel
$('#DangerEnabled').change(function () {
    CreateAlert_DangerCriteria_ShowAndHide();
});

function CreateAlert_DangerCriteria_ShowAndHide() {
    if ($("#DangerEnabled").prop('checked') === true) {
        $("#DangerCriteria").show();
    }
    else {
        $("#DangerCriteria").hide();
    }
}

// On changing the alert-type radio buttons on the 'Create Alert' page, show and hide certain UI elements
$('#CreateAlert_Type_Availability').change(function() {
    CreateAlert_Type_ShowAndHide();
});
$('#CreateAlert_Type_Threshold').change(function() {
    CreateAlert_Type_ShowAndHide();
});

// On changing the value of the 'Combination' dropdown options on the 'Create Alert' page, show and hide certain UI elements
$('#CautionCombination').change(function() {
    CreateAlert_Type_ShowAndHide();
});
$('#DangerCombination').change(function() {
    CreateAlert_Type_ShowAndHide();
});

function CreateAlert_Type_ShowAndHide() {
    // Caution
    if ($("#CreateAlert_Type_Availability").prop('checked') === true) { 
        $("#CautionNoAlertTypeSelected_Label").hide();
        $("#CautionNotificationGroupName_Label").show();
        $("#CautionNotificationGroupName").show();
        $("#CautionWindowDuration_Label").show();
        $("#CautionWindowDuration").show();
        $("#CautionStopTrackingAfter_Label").show();
        $("#CautionStopTrackingAfter").show();
        $("#CautionMinimumSampleCount_Label").hide();
        $("#CautionMinimumSampleCount").hide();
        $("#CautionOperator_Label").hide();
        $("#CautionOperator").hide();
        $("#CautionCombination_Label").hide();
        $("#CautionCombination").hide();
        $("#CautionCombinationCount_Label").hide();
        $("#CautionCombinationCount").hide();
        $("#CautionThreshold_Label").hide();
        $("#CautionThreshold").hide();
    }
    else if ($("#CreateAlert_Type_Threshold").prop('checked') === true) {
        $("#CautionNoAlertTypeSelected_Label").hide();
        $("#CautionNotificationGroupName_Label").show();
        $("#CautionNotificationGroupName").show();
        $("#CautionWindowDuration_Label").show();
        $("#CautionWindowDuration").show();
        $("#CautionStopTrackingAfter_Label").hide();
        $("#CautionStopTrackingAfter").hide();
        $("#CautionMinimumSampleCount_Label").show();
        $("#CautionMinimumSampleCount").show();
        $("#CautionOperator_Label").show();
        $("#CautionOperator").show();
        $("#CautionCombination_Label").show();
        $("#CautionCombination").show();

        var CautionCombination_Value = document.getElementById("CautionCombination").selectedIndex;
        if ((CautionCombination_Value === 3) || (CautionCombination_Value === 4)) {
            $("#CautionCombinationCount_Label").show();
            $("#CautionCombinationCount").show();
        }
        else {
            $("#CautionCombinationCount_Label").hide();
            $("#CautionCombinationCount").hide();
        }

        $("#CautionThreshold_Label").show();
        $("#CautionThreshold").show();
    }
    else {
        $("#CautionNoAlertTypeSelected_Label").show();
        $("#CautionNotificationGroupName_Label").hide();
        $("#CautionNotificationGroupName").hide();
        $("#CautionWindowDuration_Label").hide();
        $("#CautionWindowDuration").hide();
        $("#CautionStopTrackingAfter_Label").hide();
        $("#CautionStopTrackingAfter").hide();
        $("#CautionMinimumSampleCount_Label").hide();
        $("#CautionMinimumSampleCount").hide();
        $("#CautionOperator_Label").hide();
        $("#CautionOperator").hide();
        $("#CautionCombination_Label").hide();
        $("#CautionCombination").hide();
        $("#CautionCombinationCount_Label").hide();
        $("#CautionCombinationCount").hide();
        $("#CautionThreshold_Label").hide();
        $("#CautionThreshold").hide();
    }

    //Danger
    if ($("#CreateAlert_Type_Availability").prop('checked') === true) {
        $("#DangerNoAlertTypeSelected_Label").hide();
        $("#DangerNotificationGroupName_Label").show();
        $("#DangerNotificationGroupName").show();
        $("#DangerWindowDuration_Label").show();
        $("#DangerWindowDuration").show();
        $("#DangerStopTrackingAfter_Label").show();
        $("#DangerStopTrackingAfter").show();
        $("#DangerMinimumSampleCount_Label").hide();
        $("#DangerMinimumSampleCount").hide();
        $("#DangerOperator_Label").hide();
        $("#DangerOperator").hide();
        $("#DangerCombination_Label").hide();
        $("#DangerCombination").hide();
        $("#DangerCombinationCount_Label").hide();
        $("#DangerCombinationCount").hide();
        $("#DangerThreshold_Label").hide();
        $("#DangerThreshold").hide();
    }
    else if ($("#CreateAlert_Type_Threshold").prop('checked') === true) {
        $("#DangerNoAlertTypeSelected_Label").hide();
        $("#DangerNotificationGroupName_Label").show();
        $("#DangerNotificationGroupName").show();
        $("#DangerWindowDuration_Label").show();
        $("#DangerWindowDuration").show();
        $("#DangerStopTrackingAfter_Label").hide();
        $("#DangerStopTrackingAfter").hide();
        $("#DangerMinimumSampleCount_Label").show();
        $("#DangerMinimumSampleCount").show();
        $("#DangerOperator_Label").show();
        $("#DangerOperator").show();
        $("#DangerCombination_Label").show();
        $("#DangerCombination").show();

        var DangerCombination_Value = document.getElementById("DangerCombination").selectedIndex;
        if ((DangerCombination_Value === 3) || (DangerCombination_Value === 4)) {
            $("#DangerCombinationCount_Label").show();
            $("#DangerCombinationCount").show();
        }
        else {
            $("#DangerCombinationCount_Label").hide();
            $("#DangerCombinationCount").hide();
        }

        $("#DangerThreshold_Label").show();
        $("#DangerThreshold").show();
    }
    else {
        $("#DangerNoAlertTypeSelected_Label").show();
        $("#DangerNotificationGroupName_Label").hide();
        $("#DangerNotificationGroupName").hide();
        $("#DangerWindowDuration_Label").hide();
        $("#DangerWindowDuration").hide();
        $("#DangerStopTrackingAfter_Label").hide();
        $("#DangerStopTrackingAfter").hide();
        $("#DangerMinimumSampleCount_Label").hide();
        $("#DangerMinimumSampleCount").hide();
        $("#DangerOperator_Label").hide();
        $("#DangerOperator").hide();
        $("#DangerCombination_Label").hide();
        $("#DangerCombination").hide();
        $("#DangerCombinationCount_Label").hide();
        $("#DangerCombinationCount").hide();
        $("#DangerThreshold_Label").hide();
        $("#DangerThreshold").hide();
    }
}

$(function() {
    $('#CreateAlertSuspension_DateTimePicker_StartDate_Div').datetimepicker({
        pickDate: true,
        pickTime: false
    });
});

$(function() {
    $('#CreateAlertSuspension_DateTimePicker_StartTime_Div').datetimepicker({
        pickDate: false,
        pickTime: true
    });
});

function CreateAlertSuspension_Type_ShowAndHide() {
    if ($("#CreateAlertSuspension_Type_Recurring").prop('checked') === true) {
        $("#CreateAlertSuspension_DateTimePicker_StartDate_Div").show();
        $("#CreateAlertSuspension_DateTimePicker_StartDate_Label_Div").show();
        $("#CreateAlertSuspension_DateTimePicker_StartTime_Div").show();
        $("#CreateAlertSuspension_DateTimePicker_StartTime_Label_Div").show();
        $("#CreateAlertSuspension_RecurOnDays_Div").show();
        $("#CreateAlertSuspension_RecurOnDays_Label_Div").show();
        $("#CreateAlertSuspension_Duration_Div").show();
        $("#CreateAlertSuspension_Duration_Label_Div").show();
    }
    else if ($("#CreateAlertSuspension_Type_OneTime").prop('checked') === true) {
        $("#CreateAlertSuspension_DateTimePicker_StartDate_Div").show();
        $("#CreateAlertSuspension_DateTimePicker_StartDate_Label_Div").show();
        $("#CreateAlertSuspension_DateTimePicker_StartTime_Div").show();
        $("#CreateAlertSuspension_DateTimePicker_StartTime_Label_Div").show();
        $("#CreateAlertSuspension_RecurOnDays_Div").hide();
        $("#CreateAlertSuspension_RecurOnDays_Label_Div").hide();
        $("#CreateAlertSuspension_Duration_Div").show();
        $("#CreateAlertSuspension_Duration_Label_Div").show();
    }
    else {
        $("#CreateAlertSuspension_DateTimePicker_StartDate_Div").hide();
        $("#CreateAlertSuspension_DateTimePicker_StartDate_Label_Div").hide();
        $("#CreateAlertSuspension_DateTimePicker_StartTime_Div").hide();
        $("#CreateAlertSuspension_DateTimePicker_StartTime_Label_Div").hide();
        $("#CreateAlertSuspension_RecurOnDays_Div").hide();
        $("#CreateAlertSuspension_RecurOnDays_Label_Div").hide();
        $("#CreateAlertSuspension_Duration_Div").hide();
        $("#CreateAlertSuspension_Duration_Label_Div").hide();
    }
}

// On page load for 'CreateAlertSuspension', show and hide certain UI elements for 'type'
$(document).ready(function() {
    CreateAlertSuspension_Type_ShowAndHide();
});

// On changing the 'type' radio buttons 'CreateAlertSuspension', show and hide certain UI elements
$("input[type=radio]").change(function() {
    CreateAlertSuspension_Type_ShowAndHide();
});


function CreateAlertSuspension_SuspendBy_ShowAndHide() {
    if ($("#CreateAlertSuspension_SuspendBy_AlertName_Radio").prop('checked') === true) {
        $("#CreateAlertSuspension_SuspendBy_AlertName_Div").show();
        $("#CreateAlertSuspension_SuspendBy_Tags_Div").hide();
        $("#CreateAlertSuspension_SuspendBy_Everything_Div").hide();
    }
    else if ($("#CreateAlertSuspension_SuspendBy_Tags_Radio").prop('checked') === true) {
        $("#CreateAlertSuspension_SuspendBy_AlertName_Div").hide();
        $("#CreateAlertSuspension_SuspendBy_Tags_Div").show();
        $("#CreateAlertSuspension_SuspendBy_Everything_Div").hide();
    }
    else if ($("#CreateAlertSuspension_SuspendBy_Everything_Radio").prop('checked') === true) {
        $("#CreateAlertSuspension_SuspendBy_AlertName_Div").hide();
        $("#CreateAlertSuspension_SuspendBy_Tags_Div").hide();
        $("#CreateAlertSuspension_SuspendBy_Everything_Div").show();
    }
    else {
        $("#CreateAlertSuspension_SuspendBy_AlertName_Div").hide();
        $("#CreateAlertSuspension_SuspendBy_Tags_Div").hide();
        $("#CreateAlertSuspension_SuspendBy_Everything_Div").hide();
    }
}

// On page load for 'CreateAlertSuspension', show and hide certain UI elements for 'suspend by'
$(document).ready(function() {
    CreateAlertSuspension_SuspendBy_ShowAndHide();
});

// On changing the 'suspend by' radio buttons 'CreateAlertSuspension', show and hide certain UI elements
$("input[type=radio]").change(function() {
    CreateAlertSuspension_SuspendBy_ShowAndHide();
});

// Setup for the table found on the 'Alerts' page
$(document).ready(function() {
    var doesTableExist = document.getElementById('AlertsTable');

    if (doesTableExist !== null) {
        $('#AlertsTable').dataTable({
            "dom": 'C<"clear">lfrtip',
            "colVis": {"align": "right", "iOverlayFade": 200}, 
            "lengthMenu": [[15, 30, 50, -1], [15, 30, 50, "All"]],
            "order": [[0, "asc"]],
            "autoWidth": false,
            "stateSave": true,
            "iCookieDuration": 2592000, // 30 days
            "columnDefs": [
                {
                    "targets": [3],
                    "visible": false
                },
                {
                    "targets": [4],
                    "visible": false
                },
                {
                    "targets": [8],
                    "visible": false
                },
                {
                    "targets": [9],
                    "visible": false
                }
            ]}).columnFilter({
            aoColumns: [
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                },
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                },
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                },
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                },
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                },
                {type: "select", values: ['true', 'false']},
                {type: "select", values: ['true', 'false']},
                {type: "select", values: ['true', 'false']},
                {type: "select", values: ['true', 'false']},
                {type: "select", values: ['true', 'false']},
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                }
            ]
        });

        $( colvis.button() ).prependTo('#AlertsTable_filter');
    }
});

// Setup for the table found on the 'Alerts' page
$(document).ready(function() {
    var doesTableExist = document.getElementById('AlertSuspensionsTable');
    
    if (doesTableExist !== null) {
        $('#AlertSuspensionsTable').dataTable({
            "dom": 'C<"clear">lfrtip',
            "colVis": {"align": "right", "iOverlayFade": 200}, 
            "lengthMenu": [[15, 30, 50, -1], [15, 30, 50, "All"]],
            "order": [[0, "asc"]],
            "autoWidth": false,
            "stateSave": true,
            "iCookieDuration": 2592000 // 30 days
        }).columnFilter({
            aoColumns: [
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                },
                {type: "select", values: ['Alert Name', 'Metric Group Tags', 'Everything']},
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                },
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                },
                {type: "select", values: ['true', 'false']},
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                }
            ]
        });

        $( colvis.button() ).prependTo('#AlertSuspensionsTable_filter');
    }
});

// Setup for the table found on the 'MetricGroups' page
$(document).ready(function() {
    var doesTableExist = document.getElementById('MetricGroupsTable');
    
    if (doesTableExist !== null) {
        $('#MetricGroupsTable').dataTable({
            "dom": 'C<"clear">lfrtip',
            "colVis": {"align": "right", "iOverlayFade": 200}, 
            "lengthMenu": [[15, 30, 50, -1], [15, 30, 50, "All"]],
            "order": [[0, "asc"]],
            "autoWidth": false,
            "stateSave": true,
            "iCookieDuration": 2592000 // 30 days
        }).columnFilter({
            aoColumns: [
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                },
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                },
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                },
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                },
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                }
            ]
        });

        $( colvis.button() ).prependTo('#MetricGroupsTable_filter');
    }
});

// Setup for the table found on the 'NotificationGroups' page
$(document).ready(function() {
    var doesTableExist = document.getElementById('NotificationGroupsTable');
    
    if (doesTableExist !== null) {
        $('#NotificationGroupsTable').dataTable({
            "dom": 'C<"clear">lfrtip',
            "colVis": {"align": "right", "iOverlayFade": 200}, 
            "lengthMenu": [[15, 30, 50, -1], [15, 30, 50, "All"]],
            "order": [[0, "asc"]],
            "autoWidth": false,
            "stateSave": true,
            "iCookieDuration": 2592000 // 30 days
        }).columnFilter({
            aoColumns: [
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                },
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                },
                {
                    type: "text",
                    bRegex: true,
                    bSmart: true
                }
            ]
        });

        $( colvis.button() ).prependTo('#NotificationGroupsTable_filter');
    }
});

$(document).ready(function() {
    var doesAlertNameLookupExist = document.getElementById('AlertNameLookup');
    
    if (doesAlertNameLookupExist !== null) {
        var AlertNameLookup_Bloodhound = new Bloodhound({
            datumTokenizer: Bloodhound.tokenizers.whitespace,
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            remote: 'Lookup?Type=AlertName&Query=%QUERY'
        });

        AlertNameLookup_Bloodhound.initialize();

        $('#AlertNameLookup .typeahead').typeahead({
            source: AlertNameLookup_Bloodhound.ttAdapter(),
            updater: function (item) {
                return $('<div/>').html(item).text().replace(/\u00a0/g, " ");
            }
        });
    }
});

$(document).ready(function() {
    var doesMetricGroupNameLookupExist = document.getElementById('MetricGroupNameLookup');
    
    if (doesMetricGroupNameLookupExist !== null) {
        var MetricGroupNameLookup_Bloodhound = new Bloodhound({
            datumTokenizer: Bloodhound.tokenizers.whitespace,
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            remote: 'Lookup?Type=MetricGroupName&Query=%QUERY'
        });

        MetricGroupNameLookup_Bloodhound.initialize();

        $('#MetricGroupNameLookup .typeahead').typeahead({
            source: MetricGroupNameLookup_Bloodhound.ttAdapter(),
            updater: function (item) {
                return $('<div/>').html(item).text().replace(/\u00a0/g, " ");
            }
        });
    }
});

$(document).ready(function() {
    var doesCautionNotificationGroupNameLookupExist = document.getElementById('CautionNotificationGroupNameLookup');
    
    if (doesCautionNotificationGroupNameLookupExist !== null) {
        var NotificationGroupNameLookup_Bloodhound = new Bloodhound({
            datumTokenizer: Bloodhound.tokenizers.whitespace,
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            remote: 'Lookup?Type=NotificationGroupName&Query=%QUERY'
        });

        NotificationGroupNameLookup_Bloodhound.initialize();

        $('#CautionNotificationGroupNameLookup .typeahead').typeahead({
            source: NotificationGroupNameLookup_Bloodhound.ttAdapter(),
            updater: function (item) {
                return $('<div/>').html(item).text().replace(/\u00a0/g, " ");
            }
        });
    }
});

$(document).ready(function() {
    var doesDangerNotificationGroupNameLookupExist = document.getElementById('DangerNotificationGroupNameLookup');
    
    if (doesDangerNotificationGroupNameLookupExist !== null) {
        var NotificationGroupNameLookup_Bloodhound = new Bloodhound({
            datumTokenizer: Bloodhound.tokenizers.whitespace,
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            remote: 'Lookup?Type=NotificationGroupName&Query=%QUERY'
        });

        NotificationGroupNameLookup_Bloodhound.initialize();

        $('#DangerNotificationGroupNameLookup .typeahead').typeahead({
            source: NotificationGroupNameLookup_Bloodhound.ttAdapter(),
            updater: function (item) {
                return $('<div/>').html(item).text().replace(/\u00a0/g, " ");
            }
        });
    }
});
