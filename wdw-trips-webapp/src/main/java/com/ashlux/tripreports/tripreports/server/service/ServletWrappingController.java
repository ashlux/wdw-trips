package com.ashlux.tripreports.tripreports.server.service;

import java.util.Enumeration;
import java.util.Properties;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * Spring Controller implementation that mimics standard ServletWrappingController behaviour
 * (see its documentation), but with the important difference that it doesn't instantiate the
 * Servlet instance directly but delegate for this the BeanContext, so that we can also use IoC.*
 */
public class ServletWrappingController
    extends AbstractController
    implements BeanNameAware, InitializingBean, DisposableBean
{
    private Class servletClass;

    private String servletName;

    private Properties initParameters = new Properties();

    private String beanName;

    private Servlet servletInstance;

    public void setServletClass( Class servletClass )
    {
        System.out.print( "setServletClass : " + servletClass );
        this.servletClass = servletClass;
    }

    public void setServletName( String servletName )
    {
        System.out.print( "setServletName : " + servletName );
        this.servletName = servletName;
    }

    public void setInitParameters( Properties initParameters )
    {
        System.out.print( "setInitParameters : " + initParameters );
        this.initParameters = initParameters;
    }

    public void setBeanName( String name )
    {
        System.out.print( "setBeanName : " + name );
        this.beanName = name;
    }

    public void setServletInstance( Servlet servletInstance )
    {
        System.out.print( "setServletInstance : " + servletInstance );
        this.servletInstance = servletInstance;
    }

    public void afterPropertiesSet()
        throws Exception
    {
        System.out.print( "afterPropertiesSet" );
        if ( this.servletInstance == null )
        {
            throw new IllegalArgumentException( "servletInstance is required" );
        }
        if ( !Servlet.class.isAssignableFrom( servletInstance.getClass() ) )
        {
            throw new IllegalArgumentException( "servletInstance [" + this.servletClass.getName() +
                "] needs to implement interface [javax.servlet.Servlet]" );
        }
        if ( this.servletName == null )
        {
            this.servletName = this.beanName;
        }
        this.servletInstance.init( new DelegatingServletConfig() );
    }

    protected ModelAndView handleRequestInternal( HttpServletRequest request, HttpServletResponse response )
        throws Exception
    {
        System.out.print( "handleRequestInternal" );
        this.servletInstance.service( request, response );
        return null;
    }

    public void destroy()
    {
        System.out.print( "destroy" );
        this.servletInstance.destroy();
    }

    private class DelegatingServletConfig
        implements ServletConfig
    {
        public String getServletName()
        {
            return servletName;
        }

        public ServletContext getServletContext()
        {
            return getWebApplicationContext().getServletContext();
        }

        public String getInitParameter( String paramName )
        {
            return initParameters.getProperty( paramName );

        }

        public Enumeration getInitParameterNames()
        {
            return initParameters.keys();
        }
    }
}

