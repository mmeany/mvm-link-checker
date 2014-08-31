Overview
========
Based on starter application.

Maintain a list of URLs that can be checked for HTTP success invocations.

Records status of the call, including: output, duration, size, HTTP status code.

Initial Random Thoughts
=======================

Anyone can view results.
Registered users can initiate a link check
Admins can add URLs and Tags

From a result list an individual failed entry can be retried. The result set will capture multiple results per URL, defaulting to display of most recent result.

URLs can have tags applied to them.
All URLs with a specific tag can be run as a job.
Every URL has the 'all' tag applied and it cannot be removed.

At present, only provide simple HTTP GET requests for the URLs. A point of expansion would be to provide POST services and dynamic substitution of certain fields, possibly from config or even better from a script (similar to how JMeter works).

A Result stores the tag that was used to launch it, plus the user that invoked it.

Results can be viewed chronologically and filtered by tag or user

Application of one tag could be configured to automatically include one or more other tags. For example, a server name tag could apply an environment tag at the same time: server-b would add tag UAT.

In later phase add ability to schedule a link check to run periodically using Quartz scheduler or similar.

In later phase add ability to plug in a failure notification handler so people can be notified about failures (eMail, SMS, PUSH to mobile app etc.)
This could prove interesting: Notification levels (WARN, ERROR etc). Repeat Notification monitor (ignore until 2 successive failures). Notification periods (Send SMS out of hours for example).

Notes
=====

URL:    id              |- Primary Key
        address
        method          (only GET supported at present)
        name
        description
        date added
        added by
        date updated
        updated by

TAG:    name            |- Primary Key
        description
        implied         (List of other tags to apply when this one is)

TAGCLOUD: tag           |- Primary Key
        url             |
        date added
        added by
        
RUNLOG: id              |- Primary Key
        tags
        user
        date started
        date ended
        total
        failures

Result: runlog          |
        url             |- Primary Key
        attempt         |
        response code
        response body
        response headers
        response size
        time taken
        success flag



