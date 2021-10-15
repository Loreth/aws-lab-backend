package pl.grupakpkpur.awslab.dto.ec2;

import software.amazon.awssdk.services.ec2.model.Instance;
import software.amazon.awssdk.services.ec2.model.InstanceStateName;
import software.amazon.awssdk.services.ec2.model.InstanceType;

public record Ec2InstanceResponse(String instanceId, String imageId, InstanceType instanceType, InstanceStateName instanceState) {

	public static Ec2InstanceResponse fromInstance(Instance instance) {
		return new Ec2InstanceResponse(instance);
	}

	private Ec2InstanceResponse(Instance instance) {
		this(instance.instanceId(), instance.imageId(), instance.instanceType(), instance.state().name());
	}
}
