export interface Transaction {
    order: 'ENTRADA' | 'SAIDA' | '';
    userId: number;
    type: 'PRODUTO' | 'IMPOSTO' | 'CONTAS' | '';
    value: number;

    productId?: number;
    productQty?: number;
}

export interface TransactionResponse extends Transaction {
    idMovimentacao: number;
}

export const DEFAULT_TRANSACTION: Transaction = {
    order: '',
    userId: 0,
    type: '',
    value: 0,
};
