package com.jdhb.soap.configuration;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.XsdSchema;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WebServiceConfigTest {

    @Mock
    private XsdSchema xsdSchema;

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private MessageDispatcherServlet messageDispatcherServlet;

    @Mock
    private ServletRegistrationBean<MessageDispatcherServlet> servletRegistrationBean;

    @InjectMocks
    private WebServiceConfig webServiceConfig;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        webServiceConfig = new WebServiceConfig();
    }

    @Test
    public void testMessageDispatcherServlet() {
        when(applicationContext.getBean(MessageDispatcherServlet.class)).thenReturn(messageDispatcherServlet);

        ServletRegistrationBean<MessageDispatcherServlet> registrationBean = webServiceConfig.messageDispatcherServlet(applicationContext);

        assertNotNull(registrationBean);
        assertEquals("/ws/*", registrationBean.getUrlMappings().toArray()[0]);
    }


    @Test
    public void testDefaultWsdl11Definition() {
        when(applicationContext.getBean(XsdSchema.class)).thenReturn(xsdSchema);

        DefaultWsdl11Definition wsdl11Definition = webServiceConfig.defaultWsdl11Definition(xsdSchema);

        assertNotNull(wsdl11Definition);
    }

    @Test
    public void testEmployeesSchema() {
        XsdSchema schema = webServiceConfig.employeesSchema();

        assertNotNull(schema);
    }
}
