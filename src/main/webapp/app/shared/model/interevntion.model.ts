import { Moment } from 'moment';
import { IHistoriquetache } from 'app/shared/model/historiquetache.model';

export interface IInterevntion {
    id?: number;
    datedebutintervention?: Moment;
    datefinintervention?: Moment;
    duree?: string;
    coutmaindeuvre?: string;
    coutinterevntion?: string;
    solutions?: string;
    demandeinterventionId?: number;
    planprevetinfId?: number;
    intervenhistoriques?: IHistoriquetache[];
    etatId?: number;
    equipeId?: number;
}

export class Interevntion implements IInterevntion {
    constructor(
        public id?: number,
        public datedebutintervention?: Moment,
        public datefinintervention?: Moment,
        public duree?: string,
        public coutmaindeuvre?: string,
        public coutinterevntion?: string,
        public solutions?: string,
        public demandeinterventionId?: number,
        public planprevetinfId?: number,
        public intervenhistoriques?: IHistoriquetache[],
        public etatId?: number,
        public equipeId?: number
    ) {}
}
