import { IInterevntion } from 'app/shared/model/interevntion.model';

export interface IEtat {
    id?: number;
    nometat?: string;
    etatintervents?: IInterevntion[];
}

export class Etat implements IEtat {
    constructor(public id?: number, public nometat?: string, public etatintervents?: IInterevntion[]) {}
}
