package pl.grupakpkpur.awslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.grupakpkpur.awslab.dto.ec2.*;
import pl.grupakpkpur.awslab.service.Ec2Service;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${rest.mapping.ec2}")
public class Ec2Controller {

	private final Ec2Service ec2Service;

	@GetMapping("/")
	@ResponseBody
	public List<Ec2InstanceResponse> listInstances() {
		return ec2Service.getInstances();
	}

	@PostMapping("/create")
	@ResponseBody
	public String createInstance(@RequestBody Ec2CreateRequest createRequest) {
		return ec2Service.createInstance(createRequest.name());
	}

	@PostMapping("/stop")
	public void stopInstance(@RequestBody Ec2StopRequest stopRequest) {
		ec2Service.stopInstance(stopRequest.instanceId());
	}

	@PostMapping("/terminate")
	public void terminateInstance(@RequestBody Ec2TerminateRequest terminateRequest) {
		ec2Service.terminateInstance(terminateRequest.instanceId());
	}

	@PostMapping("/start")
	public void startInstance(@RequestBody Ec2StartRequest startRequest) {
		ec2Service.startInstance(startRequest.instanceId());
	}
}
