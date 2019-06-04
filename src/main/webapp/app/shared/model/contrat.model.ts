import { Moment } from 'moment';

export interface IContrat {
    id?: number;
    nomcontrat?: string;
    coutcontrat?: number;
    numerocontrat?: number;
    datedebutcontrat?: Moment;
    datefincontrat?: Moment;
    daterealisation?: Moment;
    equipementId?: number;
}

export class Contrat implements IContrat {
    constructor(
        public id?: number,
        public nomcontrat?: string,
        public coutcontrat?: number,
        public numerocontrat?: number,
        public datedebutcontrat?: Moment,
        public datefincontrat?: Moment,
        public daterealisation?: Moment,
        public equipementId?: number
    ) {}
}
