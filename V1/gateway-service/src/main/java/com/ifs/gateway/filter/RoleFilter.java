package com.ifs.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.ifs.gateway.exception.UnauthorizedAccessException;
import com.ifs.gateway.utils.JwtService;

@Component
public class RoleFilter extends AbstractGatewayFilterFactory<RoleFilter.Config> {
	Logger logger = LoggerFactory.getLogger(RoleFilter.class);
	@Autowired
	private RouteValidator validator;

	@Autowired
	JwtService jwtService;

	public RoleFilter() {
		super(Config.class);
	}

	public static class Config {
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			logger.info("check role: {}", validator.isSecuredHR.test(exchange.getRequest()));
			String userRole = exchange.getAttribute("userRole");

			if (userRole != null) {
				System.out.println(userRole);
				System.out.println("enter");
			    System.out.println("Comman=="+validator.isSecuredCommon.test(exchange.getRequest()));
                System.out.println("hr=="+validator.isSecuredInterviewer.test(exchange.getRequest()) +" ");
                System.out.println("Interviewer=="+validator.isSecuredInterviewer.test(exchange.getRequest()));
			    System.out.println(exchange.getRequest());

				
//			    // Check for HR-specific APIs first
	            if (validator.isSecuredHR.test(exchange.getRequest()) && !userRole.equals("ROLE_HR")) {
	                throw new UnauthorizedAccessException("Unauthorized access to HR resources");
	            }

	            // Check for Interviewer-specific APIs
	            if (validator.isSecuredInterviewer.test(exchange.getRequest()) && !userRole.equals("ROLE_Interviewer")) {
	                throw new UnauthorizedAccessException("Unauthorized access to Interviewer resources");
	            }

	            // Check for common APIs (if not HR or Interviewer)
	            if (!validator.isSecuredHR.test(exchange.getRequest()) && !validator.isSecuredInterviewer.test(exchange.getRequest())) {
	                // Allow access to common APIs for all roles
	                // You can add more common API checks here if needed
	            }
				System.out.println("end");
			}

			return chain.filter(exchange);
		});

	}

}