package pt.unl.fct.di.tardis.babel.iot.api.requests;

import pt.unl.fct.di.novasys.babel.generic.ProtoRequest;
import pt.unl.fct.di.tardis.babel.iot.api.DeviceHandle;
import pt.unl.fct.di.tardis.babel.iot.api.InputType;

/**
 * Request asking an IoT control protocol to sample a registered device
 * at a fixed period and emit a notification for each reading.
 * <p>
 * The period is expressed in milliseconds. The {@link InputType}
 * argument selects the read mode for sensors that expose multiple
 * (for example a Grove ultrasonic ranger that can return the same
 * distance in centimetres or inches).
 *
 * @author João Brilha (j.brilha@campus.fct.unl.pt)
 * @author João Leitão (jc.leitao@fct.unl.pt)
 */
public class IoTPeriodicInputRequest extends ProtoRequest {

    /** Default Babel request id used by this class. */
    public static final short REQUEST_ID = 4003;

    private final DeviceHandle handle;
    private final long period; // Period of measurement in milliseconds
    private final InputType inputType;

    /**
     * Builds a periodic input request with the default request id.
     *
     * @param handle    the handle of the device to sample
     * @param p         the sampling period, in milliseconds
     * @param inputType the read mode to use
     */
    public IoTPeriodicInputRequest(DeviceHandle handle, long p,
                                         InputType inputType) {
        this(REQUEST_ID, handle, p, inputType);
    }

    /**
     * Builds a periodic input request with a caller-supplied request id.
     *
     * @param id        the Babel request id to use
     * @param handle    the handle of the device to sample
     * @param p         the sampling period, in milliseconds
     * @param inputType the read mode to use
     */
    public IoTPeriodicInputRequest(short id, DeviceHandle handle, long p,
                                         InputType inputType) {
        super(id);
        this.handle = handle;
        this.period = p;
        this.inputType = inputType;
    }

    /** @return the handle of the device to sample. */
    public DeviceHandle getDeviceHandle() { return this.handle; }

    /** @return the sampling period, in milliseconds. */
    public long getPeriod() { return this.period; }

    /** @return the requested read mode. */
    public InputType getInputType() { return this.inputType; }
}
