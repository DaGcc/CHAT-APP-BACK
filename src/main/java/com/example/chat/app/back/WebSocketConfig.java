/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.chat.app.back;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //habilita el servidor websocket broker en spring
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/broker") //esta ruta es la que se usara en angular para conectarnos desde angular
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS(); //para no usar el sock nativo del html:5, ya que ese no soporta https si no ws
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/chat/"); //eventos a los cuales broker
        //notificara al cliente y estos se suscribiran
        registry.setApplicationDestinationPrefixes("/app"); //destino, lo usa el cliente 
        //cuando va a publicar algo en el broke
    }

}
