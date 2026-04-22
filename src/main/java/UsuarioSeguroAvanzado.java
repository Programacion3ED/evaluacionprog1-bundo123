public class UsuarioSeguroAvanzado {

    private String username;
    private String password;
    private int maxIntentos;
    private int intentosFallidos;
    private boolean bloqueado;
    private boolean accesoExitoso;

    public UsuarioSeguroAvanzado(String username, String password, int maxIntentos) {
        this.username = username;
        this.password = password;
        // Si maxIntentos es 0 o negativo, usar 3 por defecto
        this.maxIntentos = (maxIntentos <= 0) ? 3 : maxIntentos;
        this.intentosFallidos = 0;
        this.bloqueado = false;
        this.accesoExitoso = false;
    }

    public boolean autenticar(String passwordIngresada) {
        if (bloqueado) {
            return false;
        }
        if (password.equals(passwordIngresada)) {
            intentosFallidos = 0;
            accesoExitoso = true;
            return true;
        } else {
            intentosFallidos++;
            if (intentosFallidos >= maxIntentos) {
                bloqueado = true;
            }
            return false;
        }
    }

    public void reiniciarAcceso() {
        bloqueado = false;
        intentosFallidos = 0;
    }

    public boolean validarPasswordSegura(String pass) {
        if (pass == null || pass.length() < 8) {
            return false;
        }
        boolean tieneMayuscula = false;
        boolean tieneNumero = false;
        for (char c : pass.toCharArray()) {
            if (Character.isUpperCase(c)) tieneMayuscula = true;
            if (Character.isDigit(c)) tieneNumero = true;
        }
        return tieneMayuscula && tieneNumero;
    }

    public boolean cambiarPassword(String actual, String nueva) {
        if (bloqueado) {
            return false;
        }
        if (!password.equals(actual)) {
            return false;
        }
        if (!validarPasswordSegura(nueva)) {
            return false;
        }
        password = nueva;
        return true;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public int getMaxIntentos() {
        return maxIntentos;
    }

    public boolean isAccesoExitoso() {
        return accesoExitoso;
    }
}
