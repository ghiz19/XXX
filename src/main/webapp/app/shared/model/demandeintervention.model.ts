import { Moment } from 'moment';
import { IInterevntion } from 'app/shared/model/interevntion.model';

export interface IDemandeintervention {
    id?: number;
    description?: string;
    degreeurgence?: string;
    datedemande?: Moment;
    priorite?: string;
    dateprevu?: string;
    datereel?: string;
    demandeintervens?: IInterevntion[];
    utilisateurId?: number;
}

export class Demandeintervention implements IDemandeintervention {
    constructor(
        public id?: number,
        public description?: string,
        public degreeurgence?: string,
        public datedemande?: Moment,
        public priorite?: string,
        public dateprevu?: string,
        public datereel?: string,
        public demandeintervens?: IInterevntion[],
        public utilisateurId?: number
    ) {}
}
