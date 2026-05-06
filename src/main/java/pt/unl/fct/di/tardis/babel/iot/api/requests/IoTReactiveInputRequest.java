package pt.unl.fct.di.tardis.babel.iot.api.requests;

import pt.unl.fct.di.tardis.babel.iot.api.DeviceHandle;
import pt.unl.fct.di.tardis.babel.iot.api.Threshold;

/**
 * Request asking an IoT control protocol to monitor a registered
 * device and emit a notification only when a sampled value satisfies
 * the supplied {@link Threshold}.
 * <p>
 * The threshold predicate is evaluated by the protocol on every read
 * (the cadence of which is implementation-defined or controlled by a
 * companion periodic request); a notification is delivered to the
 * caller only when {@code threshold.test(value)} returns {@code true}.
 *
 * @param <T> the type of value produced by the targeted sensor.
 *
 * @author João Brilha (j.brilha@campus.fct.unl.pt)
 * @author João Leitão (jc.leitao@fct.unl.pt)
 */
public class IoTReactiveInputRequest<T> extends IoTInputRequest {

    private final DeviceHandle handle;
    private final Threshold<T> threshold;

    /**
     * @param id        the Babel request id
     * @param handle    the handle of the device to monitor
     * @param threshold the predicate that gates notification delivery
     */
    public IoTReactiveInputRequest(short id, DeviceHandle handle,
                                   Threshold<T> threshold) {
        super(id, handle);
        this.handle = handle;
        this.threshold = threshold;
    }

    /** @return the handle of the device to monitor. */
    public DeviceHandle getDeviceHandle() { return this.handle; }

    /** @return the predicate that gates notification delivery. */
    public Threshold<T> getThreshold() { return this.threshold; }
}
