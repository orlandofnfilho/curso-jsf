package com.algaworks.pedidovenda.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.velocity.tools.generic.NumberTool;

import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import com.algaworks.pedidovenda.util.mail.Mailer;
import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Mailer mailer;
    
    @Inject
    @PedidoEdicao
    private Pedido pedido;
    
    public void enviarPedido() {
        MailMessage message = mailer.novaMensagem();
        
        String template = loadTemplate("/emails/pedido.template");
        
        message.to(this.pedido.getCliente().getEmail())
            .subject("Pedido " + this.pedido.getId())
            .bodyHtml(new VelocityTemplate(template))
            .put("pedido", this.pedido)
            .put("numberTool", new NumberTool())
            .put("locale", new Locale("pt", "BR"))
            .send();
        System.out.println(message.getEmailMessage().toString());
        
        FacesUtil.addInfoMessage("Pedido enviado por e-mail com sucesso!");
    }
    
    private String loadTemplate(String templatePath) {
        InputStream inputStream = getClass().getResourceAsStream(templatePath);
        if (inputStream == null) {
            throw new RuntimeException("Não foi possível carregar o template: " + templatePath);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o template: " + templatePath, e);
        }
    }
}
