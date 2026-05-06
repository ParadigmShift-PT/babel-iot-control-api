package pt.unl.fct.di.tardis.babel.iot.api.requests;

import pt.unl.fct.di.novasys.babel.generic.ProtoRequest;
import pt.unl.fct.di.tardis.babel.iot.api.DeviceHandle;
// import pt.unl.fct.di.tardis.babel.iot.api.InputType;

/**
 * Base class for requests that ask an IoT control protocol for a
 * single read of a registered device.
 * <p>
 * Subclasses extend this request to add timing semantics (one-shot,
 * periodic, reactive) and to bind a more specific {@code InputType}
 * for sensors that expose multiple read modes.
 *
 * @author João Brilha (j.brilha@campus.fct.unl.pt)
 * @author João Leitão (jc.leitao@fct.unl.pt)
 */
public class IoTInputRequest extends ProtoRequest {

    // public final InputType inputType;
    private final DeviceHandle handle;

    /**
     * @param requestId the Babel request id
     * @param handle    the handle of the device to read
     */
    public IoTInputRequest(short requestId, DeviceHandle handle) {
                                // , InputType inputType) {
        super(requestId);
        this.handle = handle;
        // this.inputType = inputType;
    }

    /** @return the handle of the device to read. */
    public DeviceHandle getDeviceHandle() { return this.handle; }

    // public InputType getInputType() { return this.inputType; }
}
