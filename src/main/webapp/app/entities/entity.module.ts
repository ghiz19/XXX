import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'equipement',
                loadChildren: './equipement/equipement.module#GmaovEquipementModule'
            },
            {
                path: 'typeequipement',
                loadChildren: './typeequipement/typeequipement.module#GmaovTypeequipementModule'
            },

            {
                path: 'localisations',
                loadChildren: './localisations/localisations.module#GmaovLocalisationsModule'
            },
            {
                path: 'servicee',
                loadChildren: './servicee/servicee.module#GmaovServiceeModule'
            },
            {
                path: 'pm',
                loadChildren: './pm/pm.module#GmaovPmModule'
            },
            {
                path: 'contrat',
                loadChildren: './contrat/contrat.module#GmaovContratModule'
            },
            {
                path: 'demandeintervention',
                loadChildren: './demandeintervention/demandeintervention.module#GmaovDemandeinterventionModule'
            },
            {
                path: 'equipement',
                loadChildren: './equipement/equipement.module#GmaovEquipementModule'
            },
            {
                path: 'typeequipement',
                loadChildren: './typeequipement/typeequipement.module#GmaovTypeequipementModule'
            },
            {
                path: 'localisations',
                loadChildren: './localisations/localisations.module#GmaovLocalisationsModule'
            },
            {
                path: 'servicee',
                loadChildren: './servicee/servicee.module#GmaovServiceeModule'
            },
            {
                path: 'pm',
                loadChildren: './pm/pm.module#GmaovPmModule'
            },
            {
                path: 'contrat',
                loadChildren: './contrat/contrat.module#GmaovContratModule'
            },
            {
                path: 'planprevetinf',
                loadChildren: './planprevetinf/planprevetinf.module#GmaovPlanprevetinfModule'
            },
            {
                path: 'interevntion',
                loadChildren: './interevntion/interevntion.module#GmaovInterevntionModule'
            },
            {
                path: 'etat',
                loadChildren: './etat/etat.module#GmaovEtatModule'
            },
            {
                path: 'historiquetache',
                loadChildren: './historiquetache/historiquetache.module#GmaovHistoriquetacheModule'
            },
            {
                path: 'equipe',
                loadChildren: './equipe/equipe.module#GmaovEquipeModule'
            },
            {
                path: 'utilisateur',
                loadChildren: './utilisateur/utilisateur.module#GmaovUtilisateurModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GmaovEntityModule {}
