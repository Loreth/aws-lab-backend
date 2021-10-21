package pl.grupakpkpur.awslab.service;

import org.springframework.stereotype.Service;
import pl.grupakpkpur.awslab.model.ec2.Ec2InstanceResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class Ec2Service {

	private static final Region REGION = Region.US_EAST_1;
	private static final String AMAZON_LINUX_AMI_ID = "ami-02e136e904f3da870";
	private final Ec2Client ec2 = Ec2Client.builder().region(REGION).build();

	public Ec2InstanceResponse createInstance(String name) {
		RunInstancesRequest runRequest =
				RunInstancesRequest.builder()
						.imageId(AMAZON_LINUX_AMI_ID)
						.instanceType(InstanceType.T2_MICRO)
						.maxCount(1)
						.minCount(1)
						.build();

		RunInstancesResponse response = ec2.runInstances(runRequest);
		Instance createdInstance = response.instances().get(0);
		String instanceId = createdInstance.instanceId();

		Tag tag = Tag.builder().key("Name").value(name).build();

		CreateTagsRequest tagRequest = CreateTagsRequest.builder().resources(instanceId).tags(tag).build();

		try {
			ec2.createTags(tagRequest);
			System.out.printf("Successfully created EC2 Instance %s based on AMI %s\n", instanceId, AMAZON_LINUX_AMI_ID);

			return Ec2InstanceResponse.fromInstance(createdInstance);

		} catch (Ec2Exception exception) {
			System.err.println(exception.awsErrorDetails().errorMessage());
			return null;
		}
	}

	public void startInstance(String instanceId) {
		StartInstancesRequest request =
				StartInstancesRequest.builder()
						.instanceIds(instanceId)
						.build();

		try {
			ec2.startInstances(request);
		} catch (Ec2Exception exception) {
			System.err.println(exception.awsErrorDetails().errorMessage());
		}
		System.out.printf("Successfully started instance %s\n", instanceId);
	}

	public void stopInstance(String instanceId) {
		StopInstancesRequest request =
				StopInstancesRequest
						.builder()
						.instanceIds(instanceId)
						.build();

		try {
			ec2.stopInstances(request);
			System.out.printf("Successfully stopped instance %s\n", instanceId);
		} catch (Ec2Exception exception) {
			System.err.println(exception.awsErrorDetails().errorMessage());
		}
	}

	public void terminateInstance(String instanceId) {
		TerminateInstancesRequest request =
				TerminateInstancesRequest.builder()
						.instanceIds(instanceId)
						.build();

		try {
			ec2.terminateInstances(request);
			System.out.printf("Successfully terminated instance %s\n", instanceId);
		} catch (Ec2Exception exception) {
			System.err.println(exception.awsErrorDetails().errorMessage());
		}
	}

	public List<Ec2InstanceResponse> getInstances() {
		List<Ec2InstanceResponse> result = new ArrayList<>();

		try {
			String nextToken = null;
			do {
				DescribeInstancesRequest request = DescribeInstancesRequest.builder().maxResults(6).nextToken(nextToken).build();
				DescribeInstancesResponse response = ec2.describeInstances(request);

				if(response.reservations().isEmpty()) {
					System.out.println("no reservations found!");
				}

				for (Reservation reservation : response.reservations()) {
					for (Instance instance : reservation.instances()) {
						result.add(Ec2InstanceResponse.fromInstance(instance));
					}
				}
				nextToken = response.nextToken();
			} while (nextToken != null);

		} catch (Ec2Exception exception) {
			System.err.println(exception.awsErrorDetails().errorMessage());
			return Collections.emptyList();
		}

		return result;
	}
}
