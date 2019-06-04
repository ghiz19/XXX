export interface IPm {
    id?: number;
    libelle?: string;
    unite?: string;
    avoirplanprevetinf?: boolean;
    equipementId?: number;
}

export class Pm implements IPm {
    constructor(
        public id?: number,
        public libelle?: string,
        public unite?: string,
        public avoirplanprevetinf?: boolean,
        public equipementId?: number
    ) {
        this.avoirplanprevetinf = this.avoirplanprevetinf || false;
    }
}
