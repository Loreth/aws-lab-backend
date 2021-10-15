package pl.grupakpkpur.awslab.service;

import org.springframework.stereotype.Service;
import pl.grupakpkpur.awslab.model.Ec2InstanceDto;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class Ec2Service {

	private static final Region REGION = Region.US_EAST_1;
	private static final String AMAZON_LINUX_AMI_ID = "ami-074cce78125f09d61";
	private final Ec2Client ec2 = Ec2Client.builder().region(REGION).build();

	public String createInstance(String name) {
		RunInstancesRequest runRequest =
				RunInstancesRequest.builder()
						.imageId(AMAZON_LINUX_AMI_ID)
						.instanceType(InstanceType.T1_MICRO)
						.maxCount(1)
						.minCount(1)
						.build();

		RunInstancesResponse response = ec2.runInstances(runRequest);
		String instanceId = response.instances().get(0).instanceId();

		Tag tag = Tag.builder().key("Name").value(name).build();

		CreateTagsRequest tagRequest = CreateTagsRequest.builder().resources(instanceId).tags(tag).build();

		try {
			ec2.createTags(tagRequest);
			System.out.printf("Successfully created EC2 Instance %s based on AMI %s", instanceId, AMAZON_LINUX_AMI_ID);

			return instanceId;

		} catch (Ec2Exception e) {
			System.err.println(e.awsErrorDetails().errorMessage());
			return "";
		}
	}

	public void startInstance(String instanceId) {

	}

	public void stopInstance(long id) {

	}

	public List<Ec2InstanceDto> getInstances() {
		List<Ec2InstanceDto> result = new ArrayList<>();

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
						result.add(Ec2InstanceDto.fromInstance(instance));
					}
				}
				nextToken = response.nextToken();
			} while (nextToken != null);

		} catch (Ec2Exception e) {
			System.err.println(e.awsErrorDetails().errorMessage());
			return Collections.emptyList();
		}

		return result;
	}
}
