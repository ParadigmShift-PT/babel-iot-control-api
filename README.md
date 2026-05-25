# Babel IoT Control API

Java API surface for Babel protocols that manage IoT devices. Defines the
fundamental Babel events — requests, replies, notifications — and the
supporting types (device handles, device types, input types, threshold
predicates) used to drive sensors and actuators attached to a Raspberry Pi
gateway from any Babel application.

This artifact is API-only: no protocol implementation, no I/O. To actually
manage devices, pair it with a concrete protocol implementation
(`babel-iot-control-protocols`) and a device runtime
(`pi4j-iot-device-library`).

**Group ID:** `pt.paradigmshift.iot`
**Artifact ID:** `babel-iot-control-api`
**Current version:** `1.1.0`

> **1.1.0 is a breaking release** — the lifecycle request and reply IDs
> moved out of the `4xxx` range into the reserved API slot `500` so they
> follow the ParadigmShift workspace Babel ID convention. Consumers must
> recompile against the new IDs. See *Protocol & event identifiers* below.

---

## Protocol & event identifiers

This module follows the Babel ID convention used across the ParadigmShift
workspace: protocol IDs jump by 100; within each protocol, each handler
class (notifications, messages, requests+replies, timers) numbers from
`protocol_id + 1` upward as four independent pools.

`babel-iot-control-api` defines no concrete protocol — it is API-only. It
**reserves the notional protocol slot 500** so its shared events have an
unambiguous numbering window. No concrete protocol in the workspace may
use slot 500.

| Type | Handler class | ID | Purpose |
|---|---|---|---|
| `RegisterIoTDeviceRequest`   | request/reply | `501` | Register a new IoT device (returns a `DeviceHandle`) |
| `UnregisterIoTDeviceRequest` | request/reply | `502` | Release a previously registered device |
| `RegisterIoTDeviceReply`     | request/reply | `503` | Reply to register (continues the same handler pool — request IDs first, replies after) |
| `UnregisterIoTDeviceReply`   | request/reply | `504` | Reply to unregister |

Concrete sensor / actuator request and reply IDs live in
`babel-iot-control-protocols` (numbered under whichever concrete protocol
owns them — I2C input/output, digital input/output).

### Migration from 1.0.x

| Type | 1.0.x ID | 1.1.0 ID |
|---|---|---|
| `RegisterIoTDeviceRequest`   | request 4000 | request 501 |
| `UnregisterIoTDeviceRequest` | request 4001 | request 502 |
| `RegisterIoTDeviceReply`     | reply 4010   | reply 503   |
| `UnregisterIoTDeviceReply`   | reply 4011   | reply 504   |

---

## Origin

This library is a fork of the IoT control API originally developed at
[NOVA School of Science and Technology (NOVA FCT)](https://www.fct.unl.pt)
as part of the [TaRDIS](https://tardis-project.eu) European research project
on swarm systems (work package 6):

> **Original repository:**
> https://codelab.fct.unl.pt/di/research/tardis/wp6/iot/protocols/iot-control-api
>
> **Original authors:** João Brilha, João Leitão

The fork was created to serve as the IoT API used by the StoneFlux edge
gateway and is maintained by [ParadigmShift](https://www.paradigmshift.pt).
All original authorship is acknowledged and preserved. Additions and
modifications made after the fork are copyright ParadigmShift.

---

## API surface

| Category | Types |
|---|---|
| **Device identity** | `DeviceHandle`, `DeviceType`, `DeviceInterface` |
| **Input semantics** | `InputType` (Ultrasonic, Accelerometer, Barometer), `Threshold<T>` |
| **Lifecycle requests** | `RegisterIoTDeviceRequest`, `UnregisterIoTDeviceRequest` |
| **Event base classes** | `IoTEventRequest` (abstract), `IoTPeriodicEventRequest` (abstract), `IoTReactiveEventRequest<T>` (abstract) |
| **Replies** | `RegisterIoTDeviceReply`, `UnregisterIoTDeviceReply`, `IoTInputReply`, `ErrorCode` |
| **Notifications** | `IoTInputNotification<T>` |

`IoTEventRequest` is the abstract base for any request that targets an
already-registered device — concrete request types (one per supported
sensor / actuator operation) live in `babel-iot-control-protocols`
under the `controlprotocols.requests.input` and
`controlprotocols.requests.output` packages.

`Threshold<T>` supports `equalTo`, `notEqualTo`, `lessThan`, `greaterThan`,
`inRange`, `outsideRange`, `any`, and `none` — used by reactive input
requests to filter when a notification is delivered.

---

## Usage

Add to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>paradigmshift-repository</id>
        <name>ParadigmShift Repository</name>
        <url>https://maven.paradigmshift.pt/releases</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>pt.paradigmshift.iot</groupId>
        <artifactId>babel-iot-control-api</artifactId>
        <version>1.1.0</version>
    </dependency>
</dependencies>
```

The artifact transitively brings the Babel core
(`pt.paradigmshift.babel:babel-core`) required for `ProtoRequest`,
`ProtoReply`, and `ProtoNotification`.

### Registering a device and reacting to its readings

```java
// Inside a GenericProtocol that knows the IoT control protocol's ID:

sendRequest(
    new RegisterIoTDeviceRequest(DeviceType.GROVE_ULTRASONIC_RANGER, "front-bumper", 7),
    iotProtocolId);

// On RegisterIoTDeviceReply, take the handle and pair it with one of the
// concrete subclasses of IoTReactiveEventRequest defined in
// babel-iot-control-protocols (e.g. GetReactiveEncoderRequest), or
// derive your own:
DeviceHandle handle = reply.getDeviceHandle();

// e.g. with a concrete subclass of IoTReactiveEventRequest<Integer>:
sendRequest(
    new MyReactiveDistanceRequest(handle, Threshold.lessThan(20, Integer::compare)),
    iotProtocolId);

// Subscribe to IoTInputNotification to receive readings that pass the threshold.
```

---

## Building

Requires Java 17 and Maven 3.6+.

```bash
mvn verify    # compile + (no tests yet)
mvn package   # produces JAR, sources JAR, and Javadoc JAR
mvn deploy    # publish to maven.paradigmshift.pt (requires REPOSILITE_TOKEN)
```

## Releasing

Push a version tag — the GitHub Actions CI workflow builds and deploys
automatically:

```bash
git tag v1.1.0
git push origin v1.1.0
```

---

## Related artifacts

| Artifact | Purpose |
|---|---|
| `pt.paradigmshift.iot:babel-iot-control-api` | This artifact — Babel events and supporting types |
| `pt.unl.fct.di.novasys.babel:iot-control-protocol-v2` | Concrete Babel protocol implementing the API |
| `pt.paradigmshift.iot:pi4j-iot-device-library` | Device-level Java wrappers driven by the protocol |

---

## License

Copyright (c) 2026 ParadigmShift, Lda. See [LICENSE](LICENSE) for full terms.

Commercial use outside of ParadigmShift requires a written licence.
Contact: [info@paradigmshift.pt](mailto:info@paradigmshift.pt)
