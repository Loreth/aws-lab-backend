package pl.grupakpkpur.awslab.dto.ec2;

import software.amazon.awssdk.services.ec2.model.Instance;
import software.amazon.awssdk.services.ec2.model.InstanceStateName;
import software.amazon.awssdk.services.ec2.model.InstanceType;
import software.amazon.awssdk.services.ec2.model.Tag;

public record Ec2InstanceResponse(String id, String name, String imageId, InstanceType type, InstanceStateName state) {

	public static Ec2InstanceResponse fromInstance(Instance instance) {
		return new Ec2InstanceResponse(instance);
	}

	private Ec2InstanceResponse(Instance instance) {
		this(
				instance.instanceId(),
				instance.tags().stream()
						.filter(tag -> tag.key().equals("Name"))
						.findFirst()
						.map(Tag::key)
						.orElse(null),
				instance.imageId(),
				instance.instanceType(),
				instance.state().name());
	}
}
