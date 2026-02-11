package com.deliverutech.delivery_api.model;

public enum Role implements GranteAuthory  {
    ADMIN, CLIENTE, RESTAURANTE, ENTREGADOR, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
