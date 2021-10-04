export interface Product {
    nome: string;
    quantidade: number;
}

export interface ProductResponse extends Product {
    idProduto: number;
}
