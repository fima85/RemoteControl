package com.matigurten.tom.remotecontrol;

/**
 * Created by fima on 08/01/17.
 */

public enum  RemoteTypeEnum {
    REMOTE_CONTROL(RemoteControlActivity.class),
    JOYSTICK(JoystickActivity.class);


    private Class<?> activityClass;

    RemoteTypeEnum(Class activity) {
        this.activityClass = activity;
    }

    public Class<?> getActivityClass() {
        return activityClass;
    }
}
