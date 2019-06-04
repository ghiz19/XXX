import { IInterevntion } from 'app/shared/model/interevntion.model';

export interface IPlanprevetinf {
    id?: number;
    descriptionplan?: string;
    equipementId?: number;
    planinterventions?: IInterevntion[];
}

export class Planprevetinf implements IPlanprevetinf {
    constructor(
        public id?: number,
        public descriptionplan?: string,
        public equipementId?: number,
        public planinterventions?: IInterevntion[]
    ) {}
}
