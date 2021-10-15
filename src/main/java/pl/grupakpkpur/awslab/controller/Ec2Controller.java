package pl.grupakpkpur.awslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.grupakpkpur.awslab.model.Ec2InstanceDto;
import pl.grupakpkpur.awslab.service.Ec2Service;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${rest.mapping.ec2}")
public class Ec2Controller {

	private final Ec2Service ec2Service;

	@GetMapping("/")
	public List<Ec2InstanceDto> listInstances() {
		return ec2Service.getInstances();
	}
}
