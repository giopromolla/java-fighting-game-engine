package org.jfge.api.engine;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.name.Names;

/**
 * The Class EngineModule.
 */
public final class EngineModule extends AbstractModule {

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	public void configure(){
		loadProperties(this.binder());
		bind(Engine.class).to(EngineImpl.class);
		bind(Timer.class).to(TimerImpl.class);
	}
	
	/**
	 * Load properties.
	 *
	 * @param binder the binder
	 */
	private void loadProperties(Binder binder) {
	    InputStream stream = EngineModule.class.getResourceAsStream("/org/jfge/config/engine/engine.properties");
	    Properties engineProperties = new Properties();
	    try {
	      engineProperties.load(stream);
	      Names.bindProperties(binder, engineProperties);
	    } catch (IOException e) {
	    	binder.addError(e);
	    }
	  }
}
