import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Demandeintervention } from 'app/shared/model/demandeintervention.model';
import { DemandeinterventionService } from './demandeintervention.service';
import { DemandeinterventionComponent } from './demandeintervention.component';
import { DemandeinterventionDetailComponent } from './demandeintervention-detail.component';
import { DemandeinterventionUpdateComponent } from './demandeintervention-update.component';
import { DemandeinterventionDeletePopupComponent } from './demandeintervention-delete-dialog.component';
import { IDemandeintervention } from 'app/shared/model/demandeintervention.model';

@Injectable({ providedIn: 'root' })
export class DemandeinterventionResolve implements Resolve<IDemandeintervention> {
    constructor(private service: DemandeinterventionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDemandeintervention> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Demandeintervention>) => response.ok),
                map((demandeintervention: HttpResponse<Demandeintervention>) => demandeintervention.body)
            );
        }
        return of(new Demandeintervention());
    }
}

export const demandeinterventionRoute: Routes = [
    {
        path: '',
        component: DemandeinterventionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Demandeinterventions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: DemandeinterventionDetailComponent,
        resolve: {
            demandeintervention: DemandeinterventionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Demandeinterventions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: DemandeinterventionUpdateComponent,
        resolve: {
            demandeintervention: DemandeinterventionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Demandeinterventions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: DemandeinterventionUpdateComponent,
        resolve: {
            demandeintervention: DemandeinterventionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Demandeinterventions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const demandeinterventionPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: DemandeinterventionDeletePopupComponent,
        resolve: {
            demandeintervention: DemandeinterventionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Demandeinterventions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
