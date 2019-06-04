import { Moment } from 'moment';

export interface IHistoriquetache {
    id?: number;
    description?: string;
    datetimedebut?: Moment;
    detetimedebut?: Moment;
    interevntionId?: number;
    utilisateurId?: number;
}

export class Historiquetache implements IHistoriquetache {
    constructor(
        public id?: number,
        public description?: string,
        public datetimedebut?: Moment,
        public detetimedebut?: Moment,
        public interevntionId?: number,
        public utilisateurId?: number
    ) {}
}
