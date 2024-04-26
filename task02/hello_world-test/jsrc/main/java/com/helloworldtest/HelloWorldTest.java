package com.helloworldtest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.annotations.lambda.LambdaUrlConfig;
import com.syndicate.deployment.model.RetentionSetting;
import com.syndicate.deployment.model.lambda.url.AuthType;
import com.syndicate.deployment.model.lambda.url.InvokeMode;

import java.util.HashMap;
import java.util.Map;

@LambdaHandler(lambdaName = "hello_world-test",
	roleName = "hello_world-test-role",
	isPublishVersion = false,
	logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)
@LambdaUrlConfig(
        authType = AuthType.NONE,
        invokeMode = InvokeMode.BUFFERED
)
public class HelloWorldTest implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
		if ("/hello".equals(request.getPath())) {
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("statusCode", 200);
			resultMap.put("message", "Hello from Lambda");
			return new APIGatewayProxyResponseEvent()
					.withStatusCode(200)
					.withBody(new Gson().toJson(resultMap));
		} else {
			return new APIGatewayProxyResponseEvent()
					.withStatusCode(404)
					.withBody("Not found");
		}
	}
}

