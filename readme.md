# LazyMouse Mouse Jiggler

## About

LazyMouse is a mouse jiggler written in Java with the following features:

* Detect mouse movement
* Detect keyboard activity
* Disable network
* Enable network
* Lock screen
* Mouse and keyboard activty timeout (locks screen and disables network).


At this current time it can jiggle the mouse on any operating system but cannot
perform any other feature unless you have Windows.  There is also currently no
way to change the default values.

The default values are as follows:

*  Activity timeout is 5 minutes
*  Timeout will both disable network and lock computer


## More "Features"

If you are using Windows you can create a task and have it enable or disable the network
by passing one of the following arguments:

* enable-network
* disable-network

These will not start a new instance of LazyMouse.  To do that you must start it with no arguments.