package com.geekbrains.server;

import com.geekbrains.CommonConstants;
import com.geekbrains.server.authorization.AuthService;
import com.geekbrains.server.authorization.InMemoryAuthServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final AuthService authService;
    private static final Logger logger = LogManager.getLogger(Server.class.getName());

    private List<ClientHandler> connectedUsers;

    public Server() {
        logger.trace("Server start.");
        authService = new InMemoryAuthServiceImpl();
        logger.trace("Auth service initiated");
        try (ServerSocket server = new ServerSocket(CommonConstants.SERVER_PORT)) {
            logger.trace("Server socket started");
            authService.start();
            logger.trace("Authentication service started");
            connectedUsers = new ArrayList<>();
            while (true) {
                logger.info("Сервер ожидает подключения");
                Socket socket = server.accept();
                logger.info("Клиент подключился");
                new ClientHandler(this, socket);
                logger.trace("Client handler created");
            }
        } catch (IOException exception) {
            logger.error("Server error");
            exception.printStackTrace();
        } finally {
            authService.end();
            logger.trace("Authentication service stopped");
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public boolean isNickNameBusy(String nickName) {
        for (ClientHandler handler : connectedUsers) {
            if (handler.getNickName().equals(nickName)) {
                logger.debug("NickName " + nickName + " busy");
                return true;
            }
        }

        return false;
    }

    public synchronized void broadcastMessage(String message) {
        for (ClientHandler handler : connectedUsers) {
            handler.sendMessage(message);
        }
    }

    public synchronized void addConnectedUser(ClientHandler handler) {
        connectedUsers.add(handler);
        logger.debug("NickName " + handler.getClass() + " added");
    }

    public synchronized void disconnectUser(ClientHandler handler) {
        connectedUsers.remove(handler);
        logger.debug("NickName " + handler.getNickName() + " removed from connect list");
    }

    public String getClients() {
        StringBuilder builder = new StringBuilder("/clients ");
        for (ClientHandler user : connectedUsers) {
            builder.append(user.getNickName()).append("\n");
        }

        return builder.toString();
    }
}
