import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Typeequipement } from 'app/shared/model/typeequipement.model';
import { TypeequipementService } from './typeequipement.service';
import { TypeequipementComponent } from './typeequipement.component';
import { TypeequipementDetailComponent } from './typeequipement-detail.component';
import { TypeequipementUpdateComponent } from './typeequipement-update.component';
import { TypeequipementDeletePopupComponent } from './typeequipement-delete-dialog.component';
import { ITypeequipement } from 'app/shared/model/typeequipement.model';

@Injectable({ providedIn: 'root' })
export class TypeequipementResolve implements Resolve<ITypeequipement> {
    constructor(private service: TypeequipementService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITypeequipement> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Typeequipement>) => response.ok),
                map((typeequipement: HttpResponse<Typeequipement>) => typeequipement.body)
            );
        }
        return of(new Typeequipement());
    }
}

export const typeequipementRoute: Routes = [
    {
        path: '',
        component: TypeequipementComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Typeequipements'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: TypeequipementDetailComponent,
        resolve: {
            typeequipement: TypeequipementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Typeequipements'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: TypeequipementUpdateComponent,
        resolve: {
            typeequipement: TypeequipementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Typeequipements'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: TypeequipementUpdateComponent,
        resolve: {
            typeequipement: TypeequipementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Typeequipements'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const typeequipementPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: TypeequipementDeletePopupComponent,
        resolve: {
            typeequipement: TypeequipementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Typeequipements'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
