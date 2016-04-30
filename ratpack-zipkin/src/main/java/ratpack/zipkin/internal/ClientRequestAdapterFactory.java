/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ratpack.zipkin.internal;

import com.github.kristofa.brave.ClientRequestAdapter;
import com.github.kristofa.brave.http.ServiceNameProvider;
import com.github.kristofa.brave.http.SpanNameProvider;

import ratpack.http.client.RequestSpec;

import javax.inject.Inject;

public class ClientRequestAdapterFactory {
  private final ServiceNameProvider serviceNameProvider;
  private final SpanNameProvider spanNameProvider;

  /**
   * Constructor.
   *
   * @param serviceNameProvider the service name provider
   * @param spanNameProvider the span name provider.
   */
  @Inject
  public ClientRequestAdapterFactory(final ServiceNameProvider serviceNameProvider, final
  SpanNameProvider spanNameProvider) {
    this.serviceNameProvider = serviceNameProvider;
    this.spanNameProvider = spanNameProvider;
  }

  /**
   * Create a {@link ClientRequestAdapter} instance.
   *
   * @param requestSpec the request spec
   * @param method the http method
   *
   * @return a client request adaptor
   */
  public ClientRequestAdapter createAdaptor(final RequestSpec requestSpec, final String method) {
    return new RatpackClientRequestAdapter(requestSpec, method, serviceNameProvider, spanNameProvider);
  }
}