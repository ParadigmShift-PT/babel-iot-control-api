package pt.unl.fct.di.tardis.babel.iot.api.requests;

import pt.unl.fct.di.novasys.babel.generic.ProtoRequest;
import pt.unl.fct.di.tardis.babel.iot.api.DeviceHandle;
import pt.unl.fct.di.tardis.babel.iot.api.InputType;

public class IoTPeriodicInputRequest extends ProtoRequest {

    public static final short REQUEST_ID = 4003;

    private final DeviceHandle handle;
    private final long period; // Period of measurement in milliseconds
    private final InputType inputType;

    public IoTPeriodicInputRequest(DeviceHandle handle, long p,
                                         InputType inputType) {
        this(REQUEST_ID, handle, p, inputType);
    }

    public IoTPeriodicInputRequest(short id, DeviceHandle handle, long p,
                                         InputType inputType) {
        super(id);
        this.handle = handle;
        this.period = p;
        this.inputType = inputType;
    }

    public DeviceHandle getDeviceHandle() { return this.handle; }

    public long getPeriod() { return this.period; }

    public InputType getInputType() { return this.inputType; }
}
