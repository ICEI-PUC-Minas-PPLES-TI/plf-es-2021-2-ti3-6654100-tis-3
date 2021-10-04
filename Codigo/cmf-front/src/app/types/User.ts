export interface User {
  name: string;
  email: string;
  password: string;
  role: string;
}

export interface UserResponse extends User {
  idUsuario: number;
}

export const DEFAULT_USER: User = {
  name: '',
  email: '',
  password: '',
  role: ''
};
