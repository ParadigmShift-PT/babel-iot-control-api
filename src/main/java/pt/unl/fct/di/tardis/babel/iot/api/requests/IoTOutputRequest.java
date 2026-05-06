package pt.unl.fct.di.tardis.babel.iot.api.requests;

import pt.unl.fct.di.novasys.babel.generic.ProtoRequest;
import pt.unl.fct.di.tardis.babel.iot.api.DeviceHandle;

/**
 * Abstract base class for requests that drive an actuator (an output
 * device) registered with an IoT control protocol.
 * <p>
 * Concrete subclasses live in {@code babel-iot-control-protocols} and
 * carry the device-specific payload (text to display, colour to set,
 * tone to emit, …). Every output request is bound to the actuator it
 * targets through a {@link DeviceHandle}.
 *
 * @author João Brilha (j.brilha@campus.fct.unl.pt)
 * @author João Leitão (jc.leitao@fct.unl.pt)
 */
public abstract class IoTOutputRequest extends ProtoRequest {

	private final DeviceHandle handle;

	/**
	 * @param requestId the Babel request id
	 * @param handle    the handle of the actuator to drive
	 */
	public IoTOutputRequest(short requestId, DeviceHandle handle) {
		super(requestId);
		this.handle = handle;
	}

	/** @return the handle of the actuator targeted by this request. */
	public DeviceHandle getDeviceHandle() {
		return this.handle;
	}
}
