import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Localisations } from 'app/shared/model/localisations.model';
import { LocalisationsService } from './localisations.service';
import { LocalisationsComponent } from './localisations.component';
import { LocalisationsDetailComponent } from './localisations-detail.component';
import { LocalisationsUpdateComponent } from './localisations-update.component';
import { LocalisationsDeletePopupComponent } from './localisations-delete-dialog.component';
import { ILocalisations } from 'app/shared/model/localisations.model';

@Injectable({ providedIn: 'root' })
export class LocalisationsResolve implements Resolve<ILocalisations> {
    constructor(private service: LocalisationsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ILocalisations> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Localisations>) => response.ok),
                map((localisations: HttpResponse<Localisations>) => localisations.body)
            );
        }
        return of(new Localisations());
    }
}

export const localisationsRoute: Routes = [
    {
        path: '',
        component: LocalisationsComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Localisations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: LocalisationsDetailComponent,
        resolve: {
            localisations: LocalisationsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Localisations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: LocalisationsUpdateComponent,
        resolve: {
            localisations: LocalisationsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Localisations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: LocalisationsUpdateComponent,
        resolve: {
            localisations: LocalisationsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Localisations'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const localisationsPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: LocalisationsDeletePopupComponent,
        resolve: {
            localisations: LocalisationsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Localisations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
