package org.jfge.api.ai;


import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryProvider;

/**
 * The Class AiModule.
 */
public final class AiModule extends AbstractModule {

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(AiControllerFactory.class).toProvider(FactoryProvider.newFactory(AiControllerFactory.class , AiControllerImpl.class));
		bind(AiControllerParser.class).to(AiControllerParserImpl.class);
	}

}
