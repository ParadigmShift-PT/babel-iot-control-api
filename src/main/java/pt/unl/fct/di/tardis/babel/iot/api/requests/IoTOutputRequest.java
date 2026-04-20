package pt.unl.fct.di.tardis.babel.iot.api.requests;

import pt.unl.fct.di.novasys.babel.generic.ProtoRequest;
import pt.unl.fct.di.tardis.babel.iot.api.DeviceHandle;

public abstract class IoTOutputRequest extends ProtoRequest {

	private final DeviceHandle handle;
	
	public IoTOutputRequest(short requestId, DeviceHandle handle) {
		super(requestId);
		this.handle = handle;
	}
	
	public DeviceHandle getDeviceHandle() {
		return this.handle;
	}
}
