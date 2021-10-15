package pl.grupakpkpur.awslab.model;

import software.amazon.awssdk.services.ec2.model.Instance;
import software.amazon.awssdk.services.ec2.model.InstanceStateName;
import software.amazon.awssdk.services.ec2.model.InstanceType;

public record Ec2InstanceDto(String instanceId, String imageId, InstanceType instanceType, InstanceStateName instanceState) {

	public static Ec2InstanceDto fromInstance(Instance instance) {
		return new Ec2InstanceDto(instance);
	}

	private Ec2InstanceDto(Instance instance) {
		this(instance.instanceId(), instance.imageId(), instance.instanceType(), instance.state().name());
	}
}
