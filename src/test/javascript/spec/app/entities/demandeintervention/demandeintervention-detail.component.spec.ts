/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GmaovTestModule } from '../../../test.module';
import { DemandeinterventionDetailComponent } from 'app/entities/demandeintervention/demandeintervention-detail.component';
import { Demandeintervention } from 'app/shared/model/demandeintervention.model';

describe('Component Tests', () => {
    describe('Demandeintervention Management Detail Component', () => {
        let comp: DemandeinterventionDetailComponent;
        let fixture: ComponentFixture<DemandeinterventionDetailComponent>;
        const route = ({ data: of({ demandeintervention: new Demandeintervention(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [DemandeinterventionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DemandeinterventionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DemandeinterventionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.demandeintervention).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
