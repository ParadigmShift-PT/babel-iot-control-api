package pt.unl.fct.di.tardis.babel.iot.api.requests;

import pt.unl.fct.di.novasys.babel.generic.ProtoRequest;
import pt.unl.fct.di.tardis.babel.iot.api.DeviceHandle;
// import pt.unl.fct.di.tardis.babel.iot.api.InputType;

public class IoTInputRequest extends ProtoRequest {

    // public final InputType inputType;
    private final DeviceHandle handle;

    public IoTInputRequest(short requestId, DeviceHandle handle) {
                                // , InputType inputType) {
        super(requestId);
        this.handle = handle;
        // this.inputType = inputType;
    }

    public DeviceHandle getDeviceHandle() { return this.handle; }

    // public InputType getInputType() { return this.inputType; }
}
