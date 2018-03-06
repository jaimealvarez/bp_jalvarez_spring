package co.com.bancopopular.cuentas.servicios;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class BeanUtil implements ApplicationContextAware {

	private static ApplicationContext contexto;
	
	@Override
	public void setApplicationContext(ApplicationContext contexto) throws BeansException {
        BeanUtil.contexto = contexto;
    }
	
    public static <T> T getBean(Class<T> beanClass) {
        return contexto.getBean(beanClass);
    }
}
