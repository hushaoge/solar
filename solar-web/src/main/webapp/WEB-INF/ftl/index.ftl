<!DOCTYPE html>
<html>
<head>
    <title>Embed API Demo</title>
</head>
<body>

<!-- Step 1: Create the containing elements. -->

<section id="auth-button"></section>
<section id="view-selector"></section>
<section id="timeline"></section>
<section id="report"></section>

<!-- Step 2: Load the library. -->

<script>
    (function(w,d,s,g,js,fjs){
        g=w.gapi||(w.gapi={});g.analytics={q:[],ready:function(cb){this.q.push(cb)}};
        js=d.createElement(s);fjs=d.getElementsByTagName(s)[0];
        js.src='https://apis.google.com/js/platform.js';
        fjs.parentNode.insertBefore(js,fjs);js.onload=function(){g.load('analytics')};
    }(window,document,'script'));
</script>

<script>
    gapi.analytics.ready(function() {

        // Step 3: Authorize the user.

        var CLIENT_ID = '999514222735-krp41grgj34dtcisn6ji35a8caq7ps2h.apps.googleusercontent.com';

       gapi.analytics.auth.authorize({
            container: 'auth-button',
            clientid: CLIENT_ID,
        });
        /*
        gapi.analytics.auth.authorize({
            serverAuth: {
                access_token: 'ya29.ElmsA4xyErZ72-564HUUHPR0NjM7vwzONoUXfULm95ucSrHqCudso2YwrUx9lqqST9tea88Cy4Mm2Lun5qSoyNDxxQUBblBXdlCJq2RooEC_tb6AOhqviB0wQg'
            }
        });*/

        // Step 4: Create the view selector.

        var viewSelector = new gapi.analytics.ViewSelector({
            container: 'view-selector'
        });

        // Step 5: Create the timeline chart.

        var timeline = new gapi.analytics.googleCharts.DataChart({
            reportType: 'ga',
            query: {
                'dimensions': 'ga:date',
                'metrics': 'ga:sessions',
                'start-date': '7daysAgo',
                'end-date': 'today'//yesterday
            },
            chart: {
                type: 'LINE',
                container: 'timeline'
            }
        });

        timeline.on('success', function(result) {
            console.log(result.data);
        });
        /*
        var report = new gapi.analytics.report.Data({
            query: {
                //ids: 'ga:XXXX',
                metrics: 'ga:sessions',
                dimensions: 'ga:date',
                'end-date': 'today'//yesterday
            },
        });*/
        /*
        report.on('success', function(response) {
            console.log(response);
        });*/

        // Step 6: Hook up the components to work together.

        gapi.analytics.auth.on('success', function(response) {
           var res = response;
            viewSelector.execute();
        });

        viewSelector.on('change', function(ids) {
            var newIds = {
                query: {
                    ids: ids
                }
            }
            timeline.set(newIds).execute();
           // report.set(newIds).execute();
        });

    });
</script>
</body>
</html>