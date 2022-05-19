package org.sebi.incident;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/rest")
@RegisterRestClient(configKey = "incident-api")
public interface IncidentRestClient {
    @GET
    @Path("/incidents")
    @Fallback(DefaultIncident.class)
    List<Incident> getIncidents(@QueryParam("api_key") String apiKey);

    class DefaultIncident implements FallbackHandler<Incident> {

        @Override
        public Incident handle(ExecutionContext executionContext) {
            Incident i = new Incident();
            i.description = "fallback";
            return i;
        }
    }
}