# 🔐 Guia de Integração Frontend - Sistema de Autenticação JWT

## 📋 **Visão Geral**

Este guia contém todas as informações necessárias para integrar o frontend com o sistema de autenticação JWT da API. O sistema implementa autenticação baseada em tokens JWT com renovação automática.

---

## 🌐 **Endpoints da API**

### **Base URL**
- **Desenvolvimento**: `http://localhost:8080`
- **Produção**: `https://sua-app.onrender.com`

### **Endpoints Disponíveis**

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|--------------|
| `POST` | `/api/v1/auth/cadastro` | Cadastrar novo usuário | ❌ Não |
| `POST` | `/api/v1/auth/login` | Fazer login | ❌ Não |
| `POST` | `/api/v1/auth/renovar-token` | Renovar token JWT | ✅ Sim |
| `POST` | `/api/v1/auth/logout` | Fazer logout | ✅ Sim |

---

## 🔑 **Sistema de Autenticação**

### **⚠️ IMPORTANTE: Bearer Token OBRIGATÓRIO!**

**TODAS** as requisições para endpoints protegidos **DEVEM** incluir o token JWT no header `Authorization`:

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

**Exemplo prático:**
```javascript
// ❌ ERRADO - Sem Bearer Token
fetch('/api/v1/senado/senadores')

// ✅ CORRETO - Com Bearer Token
fetch('/api/v1/senado/senadores', {
  headers: {
    'Authorization': `Bearer ${token}`,
    'Content-Type': 'application/json'
  }
})
```

---

### **1. Fluxo de Autenticação**

```mermaid
graph TD
    A[Usuário acessa app] --> B{Token existe?}
    B -->|Não| C[Redirecionar para Login]
    B -->|Sim| D{Token válido?}
    D -->|Não| E[Renovar Token]
    D -->|Sim| F[Acessar API]
    E --> G{Renovação OK?}
    G -->|Sim| F
    G -->|Não| C
    C --> H[Login/Cadastro]
    H --> I[Salvar Token]
    I --> F
```

### **2. Estrutura do Token JWT**

```json
{
  "sub": "username",
  "email": "usuario@email.com",
  "username": "username",
  "iat": 1640995200,
  "exp": 1640998800
}
```

- **sub**: Subject (username do usuário)
- **email**: Email do usuário
- **username**: Nome de usuário
- **iat**: Issued At (timestamp de criação)
- **exp**: Expiration (timestamp de expiração)

---

## 📝 **Implementação Frontend**

### **1. Estrutura de Dados**

#### **DTOs de Requisição**

```typescript
// Login
interface LoginRequest {
  email: string;
  password: string;
}

// Cadastro
interface CadastroRequest {
  username: string;
  email: string;
  password: string;
}
```

#### **DTOs de Resposta**

```typescript
// Resposta padrão da API
interface ApiResponse<T> {
  data: T | null;
  message: string;
  success: boolean;
  statusCode: number;
}

// Resposta de login/cadastro
interface AuthResponse {
  data: string; // Token JWT
  message: string;
  success: boolean;
  statusCode: number;
}

// Resposta de usuário cadastrado
interface UsuarioResponse {
  data: {
    id: number;
    username: string;
    email: string;
    password: string; // Hash criptografado
  };
  message: string;
  success: boolean;
  statusCode: number;
}
```

### **2. Serviço de Autenticação (TypeScript/JavaScript)**

```typescript
class AuthService {
  private baseURL = 'https://projeto-poo-jkax.onrender.com/api/v1';
  private tokenKey = 'jwt_token';
  private refreshThreshold = 5 * 60 * 1000; // 5 minutos antes de expirar

  // Salvar token no localStorage
  private saveToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }

  // Recuperar token do localStorage
  private getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  // Remover token (logout)
  private removeToken(): void {
    localStorage.removeItem(this.tokenKey);
  }

  // Verificar se token está próximo do vencimento
  private isTokenNearExpiry(token: string): boolean {
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      const now = Date.now();
      const expiry = payload.exp * 1000;
      return (expiry - now) < this.refreshThreshold;
    } catch {
      return true; // Se não conseguir decodificar, considera como expirado
    }
  }

  // Fazer login
  async login(email: string, password: string): Promise<ApiResponse<string>> {
    try {
      const response = await fetch(`${this.baseURL}/api/v1/auth/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
      });

      const data = await response.json();
      
      if (data.success) {
        this.saveToken(data.data);
      }
      
      return data;
    } catch (error) {
      return {
        data: null,
        message: 'Erro de conexão',
        success: false,
        statusCode: 500
      };
    }
  }

  // Cadastrar usuário
  async cadastro(username: string, email: string, password: string): Promise<ApiResponse<any>> {
    try {
      const response = await fetch(`${this.baseURL}/api/v1/auth/cadastro`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, email, password }),
      });

      return await response.json();
    } catch (error) {
      return {
        data: null,
        message: 'Erro de conexão',
        success: false,
        statusCode: 500
      };
    }
  }

  // Renovar token
  async renovarToken(): Promise<boolean> {
    const token = this.getToken();
    if (!token) return false;

    try {
      const response = await fetch(`${this.baseURL}/api/v1/auth/renovar-token`, {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      const data = await response.json();
      
      if (data.success) {
        this.saveToken(data.data);
        return true;
      }
      
      return false;
    } catch (error) {
      return false;
    }
  }

   // Fazer requisição autenticada
   async authenticatedRequest(url: string, options: RequestInit = {}): Promise<Response> {
     let token = this.getToken();
     
     if (!token) {
       throw new Error('Usuário não autenticado');
     }

     // Verificar se token precisa ser renovado
     if (this.isTokenNearExpiry(token)) {
       const renewed = await this.renovarToken();
       if (!renewed) {
         this.removeToken();
         throw new Error('Token expirado. Faça login novamente.');
       }
       token = this.getToken()!;
     }

     return fetch(url, {
       ...options,
       headers: {
         ...options.headers,
         'Authorization': `Bearer ${token}`, // ← BEARER TOKEN OBRIGATÓRIO!
         'Content-Type': 'application/json',
       },
     });
   }

   // Fazer logout
   async logout(): Promise<boolean> {
     const token = this.getToken();
     if (token) {
       try {
         // Notificar o servidor sobre o logout
         await fetch(`${this.baseURL}/api/v1/auth/logout`, {
           method: 'POST',
           headers: {
             'Authorization': `Bearer ${token}`,
             'Content-Type': 'application/json',
           },
         });
       } catch (error) {
         console.warn('Erro ao notificar logout no servidor:', error);
         // Continua com o logout local mesmo se falhar
       }
     }
     
     this.removeToken();
     // Redirecionar para login ou página inicial
     window.location.href = '/login';
     return true;
   }

  // Verificar se usuário está autenticado
  isAuthenticated(): boolean {
    const token = this.getToken();
    if (!token) return false;

    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      const now = Date.now();
      const expiry = payload.exp * 1000;
      return expiry > now;
    } catch {
      return false;
    }
  }

  // Obter informações do usuário do token
  getUserInfo(): { username: string; email: string } | null {
    const token = this.getToken();
    if (!token) return null;

    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return {
        username: payload.username,
        email: payload.email
      };
    } catch {
      return null;
    }
  }
}

// Instância global do serviço
export const authService = new AuthService();
```

### **3. Hook React para Autenticação**

```typescript
import { useState, useEffect, createContext, useContext } from 'react';
import { authService } from './AuthService';

interface AuthContextType {
  isAuthenticated: boolean;
  user: { username: string; email: string } | null;
  login: (email: string, password: string) => Promise<boolean>;
  cadastro: (username: string, email: string, password: string) => Promise<boolean>;
  logout: () => void;
  loading: boolean;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [user, setUser] = useState<{ username: string; email: string } | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Verificar autenticação ao carregar
    const checkAuth = () => {
      const authenticated = authService.isAuthenticated();
      setIsAuthenticated(authenticated);
      setUser(authenticated ? authService.getUserInfo() : null);
      setLoading(false);
    };

    checkAuth();

    // Verificar autenticação periodicamente
    const interval = setInterval(checkAuth, 60000); // A cada minuto

    return () => clearInterval(interval);
  }, []);

  const login = async (email: string, password: string): Promise<boolean> => {
    setLoading(true);
    try {
      const response = await authService.login(email, password);
      if (response.success) {
        setIsAuthenticated(true);
        setUser(authService.getUserInfo());
        return true;
      }
      return false;
    } finally {
      setLoading(false);
    }
  };

  const cadastro = async (username: string, email: string, password: string): Promise<boolean> => {
    setLoading(true);
    try {
      const response = await authService.cadastro(username, email, password);
      return response.success;
    } finally {
      setLoading(false);
    }
  };

  const logout = async () => {
    await authService.logout();
    setIsAuthenticated(false);
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{
      isAuthenticated,
      user,
      login,
      cadastro,
      logout,
      loading
    }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth deve ser usado dentro de um AuthProvider');
  }
  return context;
};
```

### **4. Componente de Login**

```typescript
import React, { useState } from 'react';
import { useAuth } from './AuthContext';

const LoginForm: React.FC = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const { login, loading } = useAuth();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');

    if (!email || !password) {
      setError('Email e senha são obrigatórios');
      return;
    }

    const success = await login(email, password);
    if (!success) {
      setError('Email ou senha inválidos');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Email:</label>
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Senha:</label>
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
      </div>
      {error && <div style={{ color: 'red' }}>{error}</div>}
      <button type="submit" disabled={loading}>
        {loading ? 'Entrando...' : 'Entrar'}
      </button>
    </form>
  );
};

export default LoginForm;
```

### **5. Componente de Cadastro**

```typescript
import React, { useState } from 'react';
import { useAuth } from './AuthContext';

const CadastroForm: React.FC = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState(false);
  const { cadastro, loading } = useAuth();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');
    setSuccess(false);

    if (!username || !email || !password) {
      setError('Todos os campos são obrigatórios');
      return;
    }

    if (password.length < 6) {
      setError('Senha deve ter pelo menos 6 caracteres');
      return;
    }

    const success = await cadastro(username, email, password);
    if (success) {
      setSuccess(true);
      setUsername('');
      setEmail('');
      setPassword('');
    } else {
      setError('Erro ao cadastrar usuário');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Username:</label>
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Email:</label>
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Senha:</label>
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
      </div>
      {error && <div style={{ color: 'red' }}>{error}</div>}
      {success && <div style={{ color: 'green' }}>Usuário cadastrado com sucesso!</div>}
      <button type="submit" disabled={loading}>
        {loading ? 'Cadastrando...' : 'Cadastrar'}
      </button>
    </form>
  );
};

export default CadastroForm;
```

### **6. Hook para Requisições Autenticadas**

```typescript
import { useAuth } from './AuthContext';
import { authService } from './AuthService';

export const useAuthenticatedFetch = () => {
  const { isAuthenticated } = useAuth();

  const authenticatedFetch = async (url: string, options: RequestInit = {}) => {
    if (!isAuthenticated) {
      throw new Error('Usuário não autenticado');
    }

    return authService.authenticatedRequest(url, options);
  };

  return { authenticatedFetch };
};
```

### **7. Exemplo de Uso - Listar Senadores**

```typescript
import React, { useState, useEffect } from 'react';
import { useAuthenticatedFetch } from './useAuthenticatedFetch';

interface Senador {
  id: number;
  nome: string;
  // ... outros campos
}

const SenadoresList: React.FC = () => {
  const [senadores, setSenadores] = useState<Senador[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const { authenticatedFetch } = useAuthenticatedFetch();

  useEffect(() => {
    const fetchSenadores = async () => {
      try {
        setLoading(true);
        // O authenticatedFetch já adiciona o Bearer Token automaticamente!
        const response = await authenticatedFetch('https://sua-app.onrender.com/api/v1/senado/senadores');
        const data = await response.json();
        
        if (data.success) {
          setSenadores(data.data);
        } else {
          setError(data.message);
        }
      } catch (err) {
        setError('Erro ao carregar senadores');
      } finally {
        setLoading(false);
      }
    };

    fetchSenadores();
  }, [authenticatedFetch]);

  if (loading) return <div>Carregando...</div>;
  if (error) return <div>Erro: {error}</div>;

  return (
    <div>
      <h2>Senadores</h2>
      <ul>
        {senadores.map((senador) => (
          <li key={senador.id}>{senador.nome}</li>
        ))}
      </ul>
    </div>
  );
};

export default SenadoresList;
```

### **8. Exemplo Manual com Bearer Token**

```typescript
// Se você quiser fazer requisições manuais (sem usar o hook)
const fetchSenadoresManual = async () => {
  const token = localStorage.getItem('jwt_token');
  
  if (!token) {
    throw new Error('Usuário não autenticado');
  }

  const response = await fetch('https://sua-app.onrender.com/api/v1/senado/senadores', {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`, // ← BEARER TOKEN OBRIGATÓRIO!
      'Content-Type': 'application/json',
    },
  });

  if (!response.ok) {
    throw new Error('Erro na requisição');
  }

  return response.json();
};
```

---

## 🔧 **Configuração do App**

### **1. App.tsx (Exemplo)**

```typescript
import React from 'react';
import { AuthProvider } from './AuthContext';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import LoginForm from './LoginForm';
import CadastroForm from './CadastroForm';
import SenadoresList from './SenadoresList';
import { useAuth } from './AuthContext';

const ProtectedRoute: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const { isAuthenticated, loading } = useAuth();
  
  if (loading) return <div>Carregando...</div>;
  if (!isAuthenticated) return <Navigate to="/login" />;
  
  return <>{children}</>;
};

const App: React.FC = () => {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/login" element={<LoginForm />} />
          <Route path="/cadastro" element={<CadastroForm />} />
          <Route 
            path="/senadores" 
            element={
              <ProtectedRoute>
                <SenadoresList />
              </ProtectedRoute>
            } 
          />
          <Route path="/" element={<Navigate to="/senadores" />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
};

export default App;
```

---

## ⚠️ **Validações e Tratamento de Erros**

### **1. Validações do Frontend**

```typescript
const validateEmail = (email: string): boolean => {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
};

const validatePassword = (password: string): boolean => {
  return password.length >= 6;
};

const validateUsername = (username: string): boolean => {
  return username.length >= 3 && username.length <= 50;
};
```