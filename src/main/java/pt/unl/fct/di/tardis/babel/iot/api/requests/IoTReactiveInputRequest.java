package pt.unl.fct.di.tardis.babel.iot.api.requests;

import pt.unl.fct.di.tardis.babel.iot.api.DeviceHandle;
import pt.unl.fct.di.tardis.babel.iot.api.Threshold;

public class IoTReactiveInputRequest<T> extends IoTInputRequest {

    private final DeviceHandle handle;
    private final Threshold<T> threshold;

    public IoTReactiveInputRequest(short id, DeviceHandle handle,
                                   Threshold<T> threshold) {
        super(id, handle);
        this.handle = handle;
        this.threshold = threshold;
    }

    public DeviceHandle getDeviceHandle() { return this.handle; }

    public Threshold<T> getThreshold() { return this.threshold; }
}
